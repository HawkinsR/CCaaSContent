# The hibernate.cfg.xml File

## Learning Objectives

- Understand the purpose and structure of the `hibernate.cfg.xml` configuration file.
- Configure essential Hibernate properties: database connection, dialect, and schema management.
- Recognize when to use XML-based configuration versus Spring Boot's `application.properties`.
- Map entity classes through XML-based configuration.

## Why This Matters

On Wednesday, you configured Hibernate through Spring Boot's `application.properties`, which is the standard approach in modern Spring applications. However, the standalone `hibernate.cfg.xml` file remains important for several reasons: legacy projects rely on it, it provides a complete picture of Hibernate's configuration capabilities, and understanding it deepens your grasp of what Spring Boot auto-configuration does behind the scenes. Many enterprise codebases you will encounter in the field still use this format.

## The Concept

### What Is hibernate.cfg.xml?

`hibernate.cfg.xml` is Hibernate's native configuration file. It lives in the `src/main/resources` directory and tells Hibernate how to connect to the database, which SQL dialect to use, and which entity classes to manage.

### File Structure

A typical `hibernate.cfg.xml` file:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
    "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
    "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
    <session-factory>

        <!-- Database Connection Properties -->
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/company_db</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">secret</property>

        <!-- Hibernate Dialect -->
        <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>

        <!-- Schema Management -->
        <property name="hibernate.hbm2ddl.auto">update</property>

        <!-- SQL Logging -->
        <property name="hibernate.show_sql">true</property>
        <property name="hibernate.format_sql">true</property>

        <!-- Connection Pool -->
        <property name="hibernate.c3p0.min_size">5</property>
        <property name="hibernate.c3p0.max_size">20</property>
        <property name="hibernate.c3p0.timeout">300</property>

        <!-- Entity Mappings -->
        <mapping class="com.example.model.Employee"/>
        <mapping class="com.example.model.Department"/>

    </session-factory>
</hibernate-configuration>
```

### Property Reference

**Connection Properties:**

| Property                               | Purpose                                      |
|----------------------------------------|----------------------------------------------|
| `hibernate.connection.driver_class`    | JDBC driver class name.                      |
| `hibernate.connection.url`             | JDBC connection URL.                         |
| `hibernate.connection.username`        | Database username.                           |
| `hibernate.connection.password`        | Database password.                           |

**Hibernate Behavior:**

| Property                    | Purpose                                                        |
|-----------------------------|----------------------------------------------------------------|
| `hibernate.dialect`        | SQL dialect for the target database.                            |
| `hibernate.hbm2ddl.auto`  | Schema generation strategy (`none`, `validate`, `update`, `create`, `create-drop`). |
| `hibernate.show_sql`      | Logs generated SQL statements to the console.                   |
| `hibernate.format_sql`    | Pretty-prints the logged SQL.                                   |
| `hibernate.use_sql_comments` | Adds comments to SQL explaining the source operation.        |

**Connection Pooling:**

| Property                         | Purpose                                            |
|----------------------------------|----------------------------------------------------|
| `hibernate.c3p0.min_size`       | Minimum number of connections in the pool.          |
| `hibernate.c3p0.max_size`       | Maximum number of connections in the pool.          |
| `hibernate.c3p0.timeout`        | Seconds before an idle connection is removed.       |
| `hibernate.c3p0.max_statements` | Maximum number of prepared statements to cache.    |

### Dialect Configuration

The dialect tells Hibernate how to generate SQL for a specific database. Each database has its own SQL quirks (auto-increment syntax, data type names, pagination), and the dialect handles those differences.

Common dialects:

| Database     | Dialect Class                              |
|--------------|--------------------------------------------|
| MySQL        | `org.hibernate.dialect.MySQLDialect`       |
| PostgreSQL   | `org.hibernate.dialect.PostgreSQLDialect`  |
| Oracle       | `org.hibernate.dialect.OracleDialect`      |
| SQL Server   | `org.hibernate.dialect.SQLServerDialect`   |
| H2 (in-memory) | `org.hibernate.dialect.H2Dialect`       |

### XML-Based Entity Mapping (Legacy)

Before annotations became standard, entity mappings were defined in separate `.hbm.xml` files:

```xml
<!-- Employee.hbm.xml -->
<hibernate-mapping>
    <class name="com.example.model.Employee" table="employees">
        <id name="id" column="id">
            <generator class="identity"/>
        </id>
        <property name="name" column="full_name" not-null="true"/>
        <property name="department" column="department"/>
    </class>
</hibernate-mapping>
```

Referenced in `hibernate.cfg.xml`:

```xml
<mapping resource="com/example/model/Employee.hbm.xml"/>
```

While annotation-based mapping (covered yesterday) is now the standard, you may encounter XML mappings in older codebases.

### Using hibernate.cfg.xml Programmatically

Without Spring Boot, you bootstrap Hibernate manually:

```java
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
```

The `Configuration` object reads `hibernate.cfg.xml`, registers mapped entities, and builds a `SessionFactory` -- the central factory for creating database sessions.

### XML Config vs Spring Boot Properties

| Aspect                  | hibernate.cfg.xml                  | application.properties              |
|-------------------------|------------------------------------|--------------------------------------|
| Framework dependency    | Standalone Hibernate               | Requires Spring Boot                 |
| Location                | `src/main/resources`               | `src/main/resources`                 |
| Entity registration     | Explicit `<mapping>` tags          | Automatic via `@EntityScan`          |
| Connection pooling      | Manual (C3P0, etc.)                | Auto-configured (HikariCP default)   |
| Environment overrides   | Requires programmatic changes      | Supports profiles and env variables  |

In a Spring Boot project, `application.properties` is preferred because Spring handles the `SessionFactory`/`EntityManagerFactory` creation automatically. Understanding `hibernate.cfg.xml` is still valuable for working with legacy code and for understanding what Spring Boot abstracts away.

## Summary

- `hibernate.cfg.xml` is Hibernate's native configuration file for database connections, dialect, schema management, and entity registration.
- Dialect classes tell Hibernate how to generate database-specific SQL.
- XML-based entity mapping (`.hbm.xml` files) is the legacy approach; annotation-based mapping is the modern standard.
- In Spring Boot, `application.properties` replaces `hibernate.cfg.xml`, but the underlying concepts are the same.
- Understanding the configuration file prepares you for the Hibernate interfaces and features we will explore next.

## Additional Resources

- [Hibernate User Guide -- Configuration](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#configurations)
- [Baeldung -- Hibernate Configuration](https://www.baeldung.com/hibernate-4-spring)
- [Hibernate Dialect Reference](https://docs.jboss.org/hibernate/orm/current/javadocs/org/hibernate/dialect/package-summary.html)
