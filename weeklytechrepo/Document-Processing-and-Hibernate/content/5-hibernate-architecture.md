# Hibernate Architecture

## Learning Objectives

- Understand the layered architecture of Hibernate and how it sits between Java application code and the database.
- Identify the role of each major component: Configuration, SessionFactory, Session, Transaction, and connection pool.
- Apply the Service Locator design pattern to manage Hibernate resources in enterprise applications.
- Understand how Hibernate fits into the broader architecture of a Spring Boot application.

## Why This Matters

Throughout this week, you have configured Hibernate, mapped entities, and used its features -- Criteria API, Named Queries, and caching. Understanding Hibernate's internal architecture ties all of those pieces together. Knowing how sessions, transactions, and the connection pool interact helps you write more efficient code, debug performance issues, and make informed architectural decisions when designing enterprise applications.

## The Concept

### Hibernate Architecture Overview

Hibernate operates as a middleware layer between Java application code and the relational database. Its architecture can be visualized in layers:

```
+--------------------------------------------------+
|              Java Application Code               |
|   (Entities, DAOs, Services, Controllers)        |
+--------------------------------------------------+
                      |
                      v
+--------------------------------------------------+
|              Hibernate ORM Layer                 |
|   +------------------------------------------+  |
|   |  Configuration                           |  |
|   |  SessionFactory                          |  |
|   |  Session                                 |  |
|   |  Transaction                             |  |
|   |  Query (HQL, Criteria, Native SQL)       |  |
|   |  First-Level Cache                       |  |
|   |  Second-Level Cache                      |  |
|   +------------------------------------------+  |
+--------------------------------------------------+
                      |
                      v
+--------------------------------------------------+
|              JDBC / Connection Pool              |
|   (HikariCP, C3P0, etc.)                        |
+--------------------------------------------------+
                      |
                      v
+--------------------------------------------------+
|              Relational Database                 |
|   (MySQL, PostgreSQL, Oracle, etc.)              |
+--------------------------------------------------+
```

### Component Responsibilities

**Configuration Layer:**

- Reads `hibernate.cfg.xml` or Spring Boot's `application.properties`.
- Loads mapping metadata (annotations or XML descriptors).
- Builds the `SessionFactory`.
- This is a startup-time operation that happens once.

**SessionFactory:**

- Created from `Configuration`; one per database.
- Thread-safe and immutable after creation.
- Expensive to instantiate (parses all mappings, prepares query plans, initializes the second-level cache).
- Acts as a factory for `Session` objects.
- Holds the compiled mapping metadata and the second-level cache.

**Session:**

- Lightweight, single-threaded unit of work.
- Wraps a JDBC connection (borrowed from the connection pool).
- Provides the API for CRUD operations: `persist()`, `get()`, `merge()`, `remove()`.
- Manages the first-level cache (identity map) -- ensures that within a session, each database row is represented by exactly one Java object.
- Should be opened, used, and closed within a well-defined scope (typically a single request or transaction).

**Transaction:**

- Wraps the database transaction semantics (begin, commit, rollback).
- In Spring-managed applications, transactions are typically handled declaratively with `@Transactional`.
- In standalone Hibernate, you manage the `Transaction` object explicitly.

**Connection Pool:**

- Manages a pool of reusable JDBC connections.
- Avoids the overhead of creating a new connection for every session.
- Spring Boot defaults to HikariCP (high-performance pool). Standalone Hibernate often uses C3P0.

### The Request Lifecycle

In a typical Spring Boot web application, a single HTTP request flows through Hibernate like this:

1. The request arrives at a `@RestController` method.
2. Spring opens a `Session` (via the `OpenSessionInViewFilter` or `@Transactional`).
3. A `Transaction` is started.
4. The service layer performs business logic, using the `Session` to load and modify entities.
5. The session's first-level cache tracks all loaded entities and detects changes (dirty checking).
6. On `commit()`, Hibernate flushes pending changes -- generating and executing SQL `INSERT`, `UPDATE`, or `DELETE` statements as needed.
7. The `Transaction` commits (or rolls back on error).
8. The `Session` is closed, returning the JDBC connection to the pool.

### Dirty Checking

One of Hibernate's most powerful features is automatic dirty checking. When you load an entity via `session.get()` and modify one of its fields, you do not need to explicitly call `save()` or `update()`. Hibernate detects the change at flush time by comparing the current state against the snapshot it took when the entity was first loaded.

```java
Session session = sessionFactory.openSession();
Transaction tx = session.beginTransaction();

Employee emp = session.get(Employee.class, 101L);
emp.setDepartment("Research");  // No explicit save/update needed

tx.commit();  // Hibernate detects the change and generates an UPDATE
session.close();
```

### The Service Locator Design Pattern

The Service Locator pattern provides a centralized registry for locating services (such as the `SessionFactory`). In Hibernate applications that do not use Spring, this pattern is commonly used to manage the singleton `SessionFactory`.

```java
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(
                "SessionFactory creation failed: " + e.getMessage()
            );
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }
}
```

Usage in a DAO class:

```java
public class EmployeeDao {

    public Employee findById(Long id) {
        try (Session session = HibernateUtil.openSession()) {
            return session.get(Employee.class, id);
        }
    }

    public void save(Employee employee) {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(employee);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
```

**Why use Service Locator?**

- Ensures a single `SessionFactory` instance is shared across the entire application.
- Provides a clean access point for DAO classes.
- In Spring Boot, this pattern is unnecessary because Spring manages the `SessionFactory` as a bean and injects it automatically.

### Hibernate in Spring Boot Architecture

In a Spring Boot application, most of these components are managed automatically:

| Component          | Managed By                               |
|--------------------|------------------------------------------|
| `DataSource`       | Spring Boot auto-configuration (HikariCP)|
| `EntityManagerFactory` (equivalent to SessionFactory) | Spring Boot auto-configuration |
| `Session`/`EntityManager` | Spring's `@Transactional` proxy   |
| `Transaction`      | Spring's `PlatformTransactionManager`    |

This means your application code typically interacts only with `@Repository` classes that use `EntityManager`:

```java
@Repository
public class EmployeeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Transactional
    public void save(Employee employee) {
        entityManager.persist(employee);
    }
}
```

Spring handles session opening, transaction management, and connection pool cleanup behind the scenes.

## Summary

- Hibernate sits between your Java application and the database, providing an abstraction layer over JDBC.
- The `SessionFactory` is a heavyweight, thread-safe singleton created once at startup; the `Session` is a lightweight, short-lived unit of work.
- Dirty checking automatically detects entity modifications and generates the appropriate SQL at flush time.
- The Service Locator pattern provides centralized access to the `SessionFactory` in non-Spring applications.
- In Spring Boot, all of these components are auto-configured and managed, but understanding their operation is critical for debugging and optimization.

## Additional Resources

- [Hibernate Architecture -- Official User Guide](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#architecture)
- [Baeldung -- Guide to Hibernate Architecture](https://www.baeldung.com/hibernate-architecture)
- [Baeldung -- Service Locator Pattern](https://www.baeldung.com/java-service-locator-pattern)
