# Hibernate Features

## Learning Objectives

- Understand Hibernate's core interfaces: Session, SessionFactory, Configuration, and Transaction.
- Use the Criteria API to build type-safe, programmatic queries.
- Execute Native SQL queries through Hibernate when needed.
- Define and use Named Queries for reusable query definitions.
- Understand Hibernate's first-level (L1) and second-level (L2) caching strategies.

## Why This Matters

Yesterday you configured Hibernate and mapped entity classes to database tables. Configuration gets data into the database -- but features are what make Hibernate a powerful tool rather than a simple SQL generator. The interfaces, query mechanisms, and caching strategies covered here are what you will use daily when building data-driven applications. Mastering these features is essential before exploring Hibernate's overall architecture and advanced querying capabilities on Friday.

## The Concept

### Core Hibernate Interfaces

Hibernate's API is built around four primary interfaces:

**Configuration:**

The `Configuration` object loads Hibernate settings (from `hibernate.cfg.xml` or programmatically) and builds the `SessionFactory`. It is used once during application startup.

```java
Configuration configuration = new Configuration();
configuration.configure("hibernate.cfg.xml");
SessionFactory sessionFactory = configuration.buildSessionFactory();
```

**SessionFactory:**

A heavyweight, thread-safe object that creates `Session` instances. There is typically one `SessionFactory` per database in an application. It is expensive to create and should be reused.

```java
SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
```

**Session:**

A lightweight, short-lived object that represents a single unit of work with the database. It wraps a JDBC connection and provides methods for CRUD operations.

```java
Session session = sessionFactory.openSession();
Transaction tx = session.beginTransaction();

Employee emp = new Employee("Alice", "Engineering");
session.persist(emp);

tx.commit();
session.close();
```

Key `Session` methods:

| Method           | Purpose                                                   |
|------------------|-----------------------------------------------------------|
| `persist()`      | Saves a new entity (makes it persistent).                 |
| `get()`          | Retrieves an entity by primary key (returns null if not found). |
| `find()`         | JPA equivalent of `get()`.                                |
| `merge()`        | Updates a detached entity by merging its state.           |
| `remove()`       | Deletes a persistent entity.                              |
| `createQuery()`  | Creates an HQL or JPQL query.                             |
| `flush()`        | Synchronizes the session state with the database.         |
| `clear()`        | Evicts all entities from the session cache.               |

**Transaction:**

Wraps a database transaction. Operations should be grouped within a transaction to ensure atomicity.

```java
Transaction tx = null;
try {
    tx = session.beginTransaction();

    Employee emp = session.get(Employee.class, 101L);
    emp.setDepartment("Research");

    tx.commit();
} catch (Exception e) {
    if (tx != null) tx.rollback();
    throw e;
} finally {
    session.close();
}
```

### Criteria API

The Criteria API provides a programmatic, type-safe way to build queries without writing HQL or SQL strings. It is particularly useful when queries need to be constructed dynamically (e.g., search filters).

**Basic Query:**

```java
Session session = sessionFactory.openSession();
CriteriaBuilder cb = session.getCriteriaBuilder();
CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
Root<Employee> root = query.from(Employee.class);

query.select(root);

List<Employee> employees = session.createQuery(query).getResultList();
```

**With Conditions (WHERE clause):**

```java
CriteriaBuilder cb = session.getCriteriaBuilder();
CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
Root<Employee> root = query.from(Employee.class);

query.select(root)
     .where(cb.equal(root.get("department"), "Engineering"));

List<Employee> engineers = session.createQuery(query).getResultList();
```

**Multiple Conditions:**

```java
query.select(root)
     .where(
         cb.and(
             cb.equal(root.get("department"), "Engineering"),
             cb.greaterThan(root.get("salary"), 50000)
         )
     );
```

**Ordering:**

```java
query.select(root)
     .orderBy(cb.asc(root.get("name")));
```

**Common CriteriaBuilder Methods:**

| Method               | SQL Equivalent       |
|----------------------|----------------------|
| `cb.equal()`         | `=`                  |
| `cb.notEqual()`      | `<>` or `!=`         |
| `cb.greaterThan()`   | `>`                  |
| `cb.lessThan()`      | `<`                  |
| `cb.like()`          | `LIKE`               |
| `cb.between()`       | `BETWEEN`            |
| `cb.isNull()`        | `IS NULL`            |
| `cb.and()`           | `AND`                |
| `cb.or()`            | `OR`                 |

### Native SQL

Sometimes you need database-specific SQL that cannot be expressed in HQL or the Criteria API (window functions, vendor-specific syntax, etc.). Hibernate supports executing raw SQL through `createNativeQuery()`:

```java
List<Employee> employees = session.createNativeQuery(
    "SELECT * FROM employees WHERE department = :dept", Employee.class
).setParameter("dept", "Engineering")
 .getResultList();
```

**When to use Native SQL:**

- Database-specific features (e.g., MySQL's `GROUP_CONCAT`, PostgreSQL's `LATERAL JOIN`).
- Performance-critical queries where hand-tuned SQL outperforms generated SQL.
- Stored procedure calls.

**Trade-off:** Native SQL bypasses Hibernate's dialect abstraction, making your code database-dependent.

### Named Queries

Named Queries are pre-defined, reusable queries declared on an entity class using annotations. They are parsed and validated at startup, which catches syntax errors early.

**Defining a Named Query:**

```java
@Entity
@NamedQuery(
    name = "Employee.findByDepartment",
    query = "SELECT e FROM Employee e WHERE e.department = :dept"
)
@NamedQuery(
    name = "Employee.findActive",
    query = "SELECT e FROM Employee e WHERE e.active = true"
)
public class Employee {
    // fields, constructors, getters, setters
}
```

**Executing a Named Query:**

```java
List<Employee> engineers = session
    .createNamedQuery("Employee.findByDepartment", Employee.class)
    .setParameter("dept", "Engineering")
    .getResultList();
```

**Advantages:**

- Validated at application startup (fail-fast).
- Centralized query definitions on the entity class.
- Can be optimized by Hibernate (query plan caching).

### Caching

Hibernate provides two levels of caching to reduce database round-trips:

**First-Level Cache (L1):**

- Bound to the `Session` object.
- Always enabled; cannot be disabled.
- Ensures that within a single session, the same entity is loaded only once.
- Cleared when the session is closed.

```java
// Both calls return the same object instance
Employee emp1 = session.get(Employee.class, 101L);  // Hits the database
Employee emp2 = session.get(Employee.class, 101L);  // Returns cached instance
System.out.println(emp1 == emp2);  // true
```

**Second-Level Cache (L2):**

- Bound to the `SessionFactory` (shared across sessions).
- Must be explicitly enabled and configured.
- Requires a cache provider (Ehcache, Infinispan, Hazelcast).
- Best suited for read-heavy, rarely modified data (lookup tables, reference data).

Enabling L2 cache:

```properties
hibernate.cache.use_second_level_cache=true
hibernate.cache.region.factory_class=org.hibernate.cache.jcache.internal.JCacheRegionFactory
```

Annotating an entity for caching:

```java
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Department {
    // ...
}
```

**Cache concurrency strategies:**

| Strategy             | Use Case                                               |
|----------------------|--------------------------------------------------------|
| `READ_ONLY`          | Data that never changes.                               |
| `NONSTRICT_READ_WRITE` | Data rarely modified; eventual consistency acceptable. |
| `READ_WRITE`         | Data that is read and written; uses soft locks.        |
| `TRANSACTIONAL`      | Full transactional cache (requires JTA).               |

## Summary

- Hibernate's core interfaces -- `Configuration`, `SessionFactory`, `Session`, and `Transaction` -- form the API you use to interact with the database.
- The Criteria API enables type-safe, dynamic query construction without writing query strings.
- Native SQL is available for database-specific operations but reduces portability.
- Named Queries centralize reusable queries on entity classes and are validated at startup.
- First-level cache is automatic and session-scoped; second-level cache is cross-session and requires explicit configuration.
- Tomorrow, we will explore Hibernate's architecture as a whole and dive into HQL for advanced querying.

## Additional Resources

- [Hibernate User Guide -- Criteria Queries](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#criteria)
- [Baeldung -- Hibernate Named Query](https://www.baeldung.com/hibernate-named-query)
- [Baeldung -- Hibernate Second-Level Cache](https://www.baeldung.com/hibernate-second-level-cache)
