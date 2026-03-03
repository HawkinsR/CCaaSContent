# Advanced Hibernate

## Learning Objectives

- Write HQL (Hibernate Query Language) queries for data retrieval, filtering, aggregation, and joins.
- Understand ACID properties in the context of Hibernate transactions.
- Identify and manage Hibernate object states: transient, persistent, detached, and removed.
- Construct advanced queries using joins, subqueries, and named parameters.

## Why This Matters

This topic represents the culmination of the week's Hibernate learning path. You have progressed from ORM fundamentals (Wednesday) through configuration and features (Thursday) and architecture (earlier today). HQL, object lifecycle management, and transaction integrity are the skills that separate a developer who can set up Hibernate from one who can use it effectively in production. These advanced capabilities enable you to write efficient data access code while maintaining the reliability guarantees that enterprise applications demand.

## The Concept

### HQL -- Hibernate Query Language

HQL is Hibernate's object-oriented query language. It looks similar to SQL, but operates on **entity classes and their fields** rather than database tables and columns.

**Basic SELECT:**

```java
// SQL:  SELECT * FROM employees
// HQL equivalent:
List<Employee> employees = session.createQuery(
    "FROM Employee", Employee.class
).getResultList();
```

**WHERE clause with named parameters:**

```java
List<Employee> engineers = session.createQuery(
    "FROM Employee e WHERE e.department = :dept", Employee.class
).setParameter("dept", "Engineering")
 .getResultList();
```

Named parameters (`:paramName`) are preferred over positional parameters (`?1`) because they are self-documenting and can be reused in the same query.

**SELECT specific fields:**

```java
List<String> names = session.createQuery(
    "SELECT e.name FROM Employee e WHERE e.active = true", String.class
).getResultList();
```

**Aggregate functions:**

```java
Long count = session.createQuery(
    "SELECT COUNT(e) FROM Employee e WHERE e.department = :dept", Long.class
).setParameter("dept", "Engineering")
 .getSingleResult();

Double avgSalary = session.createQuery(
    "SELECT AVG(e.salary) FROM Employee e", Double.class
).getSingleResult();
```

Supported aggregate functions: `COUNT`, `SUM`, `AVG`, `MIN`, `MAX`.

**GROUP BY and HAVING:**

```java
List<Object[]> results = session.createQuery(
    "SELECT e.department, COUNT(e) FROM Employee e " +
    "GROUP BY e.department HAVING COUNT(e) > 5"
).getResultList();

for (Object[] row : results) {
    System.out.println("Department: " + row[0] + ", Count: " + row[1]);
}
```

### HQL Joins

HQL supports implicit and explicit joins based on entity relationships:

**Implicit Join (dot navigation):**

```java
// Navigates the ManyToOne relationship from Employee to Department
List<Employee> employees = session.createQuery(
    "FROM Employee e WHERE e.department.name = 'Engineering'", Employee.class
).getResultList();
```

**Explicit JOIN:**

```java
List<Employee> employees = session.createQuery(
    "SELECT e FROM Employee e JOIN e.department d WHERE d.name = :deptName",
    Employee.class
).setParameter("deptName", "Engineering")
 .getResultList();
```

**LEFT JOIN (includes employees without a department):**

```java
List<Employee> allEmployees = session.createQuery(
    "SELECT e FROM Employee e LEFT JOIN e.department d",
    Employee.class
).getResultList();
```

**FETCH JOIN (eager loading for a specific query):**

```java
List<Department> departments = session.createQuery(
    "SELECT d FROM Department d JOIN FETCH d.employees", Department.class
).getResultList();
```

`JOIN FETCH` overrides the lazy loading configuration for that specific query, loading the associated collection in a single SQL statement. This is one of the most important tools for avoiding the N+1 query problem.

### HQL Subqueries

HQL supports subqueries in the `WHERE` and `HAVING` clauses:

```java
// Find employees who earn more than the average salary
List<Employee> highEarners = session.createQuery(
    "FROM Employee e WHERE e.salary > " +
    "(SELECT AVG(e2.salary) FROM Employee e2)",
    Employee.class
).getResultList();
```

```java
// Find departments that have at least one active employee
List<Department> activeDepts = session.createQuery(
    "FROM Department d WHERE EXISTS " +
    "(SELECT 1 FROM Employee e WHERE e.department = d AND e.active = true)",
    Department.class
).getResultList();
```

### ACID Properties

ACID is an acronym for the four guarantees that a database transaction must provide:

| Property        | Definition                                                       |
|-----------------|------------------------------------------------------------------|
| **Atomicity**   | A transaction is all-or-nothing. If any part fails, the entire transaction is rolled back. |
| **Consistency** | A transaction moves the database from one valid state to another. Constraints (foreign keys, checks) are always satisfied. |
| **Isolation**   | Concurrent transactions do not interfere with each other. Each transaction sees a consistent snapshot of the data. |
| **Durability**  | Once committed, the transaction's changes survive system crashes. Data is written to persistent storage. |

### ACID in Hibernate

Hibernate transactions map directly to database transactions:

```java
Session session = sessionFactory.openSession();
Transaction tx = session.beginTransaction();

try {
    Employee emp1 = session.get(Employee.class, 1L);
    emp1.setSalary(emp1.getSalary() + 5000);

    Employee emp2 = session.get(Employee.class, 2L);
    emp2.setDepartment("Research");

    // Both changes succeed together (Atomicity)
    tx.commit();
} catch (Exception e) {
    // Both changes are rolled back together (Atomicity)
    tx.rollback();
    throw e;
} finally {
    session.close();
}
```

In Spring Boot, `@Transactional` declaratively manages this:

```java
@Service
public class EmployeeService {

    @Transactional
    public void promoteEmployee(Long id) {
        Employee emp = employeeRepository.findById(id);
        emp.setSalary(emp.getSalary() + 5000);
        emp.setTitle("Senior " + emp.getTitle());
        // If an exception occurs, both changes roll back automatically
    }
}
```

### Transaction Isolation Levels

Isolation levels control how much of other transactions' uncommitted data is visible:

| Level              | Dirty Reads | Non-Repeatable Reads | Phantom Reads |
|--------------------|-------------|----------------------|---------------|
| READ_UNCOMMITTED   | Possible    | Possible             | Possible      |
| READ_COMMITTED     | Prevented   | Possible             | Possible      |
| REPEATABLE_READ    | Prevented   | Prevented            | Possible      |
| SERIALIZABLE       | Prevented   | Prevented            | Prevented     |

Most databases default to `READ_COMMITTED`. Higher isolation levels provide stronger guarantees but reduce concurrency.

### Object States

Every Hibernate entity exists in one of four states:

**Transient:**

- The object is created with `new` but not yet associated with a Hibernate session.
- It has no representation in the database.

```java
Employee emp = new Employee("Alice", "Engineering");
// emp is TRANSIENT -- not tracked by Hibernate
```

**Persistent:**

- The object is associated with an open session and has a corresponding row in the database.
- Changes to persistent objects are automatically detected (dirty checking) and synchronized at flush/commit time.

```java
session.persist(emp);
// emp is now PERSISTENT -- tracked and synced
```

**Detached:**

- The object was previously persistent, but the session has been closed.
- Changes to detached objects are NOT automatically saved. You must re-attach them with `session.merge()`.

```java
session.close();
// emp is now DETACHED -- still has an ID but is no longer tracked

Session newSession = sessionFactory.openSession();
Transaction tx = newSession.beginTransaction();
Employee merged = newSession.merge(emp);  // Re-attach
tx.commit();
```

**Removed:**

- The object is marked for deletion. It will be deleted from the database at flush/commit time.

```java
session.remove(emp);
// emp is REMOVED -- will be deleted on commit
```

### State Transition Diagram

```
                persist()
  TRANSIENT  ------------>  PERSISTENT
                                |
                                | session.close() / session.clear()
                                v
                            DETACHED
                                |
                                | merge()
                                v
                            PERSISTENT
                                |
                                | remove()
                                v
                             REMOVED
                                |
                                | commit()
                                v
                        (Deleted from DB)
```

Understanding these states is critical for debugging issues like `LazyInitializationException` (accessing a lazy collection on a detached entity) and `DetachedEntityPassedToPersistException` (passing a detached entity to `persist()` instead of `merge()`).

## Summary

- HQL is Hibernate's object-oriented query language, operating on entity classes rather than database tables.
- Named parameters, aggregate functions, GROUP BY, and subqueries provide SQL-equivalent power with type safety.
- JOIN FETCH solves the N+1 query problem by eagerly loading associations in a single query.
- ACID properties (Atomicity, Consistency, Isolation, Durability) are fundamental transaction guarantees that Hibernate enforces through its Transaction API and Spring's `@Transactional`.
- Object states (transient, persistent, detached, removed) define how Hibernate tracks and synchronizes entity changes.
- Together, this week's content has taken you from document processing (XML, JSON, JMS) through the full Hibernate stack -- preparing you for the data access patterns you will use in your projects and in the microservices architecture covered next week.

## Additional Resources

- [Hibernate User Guide -- HQL and JPQL](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#query-language)
- [Baeldung -- HQL Tutorial](https://www.baeldung.com/hibernate-hql-queries)
- [Baeldung -- Entity Lifecycle in Hibernate](https://www.baeldung.com/hibernate-entity-lifecycle)
