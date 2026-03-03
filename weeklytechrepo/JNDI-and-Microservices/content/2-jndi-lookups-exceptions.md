# JNDI Lookups & Common Exceptions

## Learning Objectives

- Understand how to retrieve bound objects from a JNDI Context.
- Identify and troubleshoot common JNDI exceptions, specifically `NameNotFoundException`.

## Why This Matters

If Monday taught us how application servers *provide* resources (like database connections) to the application environment, today focuses on how to *consume* them. While modern frameworks like Spring Boot use Dependency Injection (`@Autowired`) to manage resources automatically, legacy systems and traditional Java EE applications rely heavily on JNDI lookups. Understanding how to manually retrieve these objects—and what to do when they are missing—is a vital debugging skill for enterprise developers.

## The Concept

### Looking Up JNDI Objects

A JNDI lookup is the process of asking the directory service for a resource by its unique name. This requires obtaining an `InitialContext` and calling its `lookup()` method.

Because JNDI is generic and can store any type of object, the `lookup()` method returns a generic `java.lang.Object`. It is the developer's responsibility to cast this returned object to the expected type (e.g., `DataSource`, `QueueConnectionFactory`).

The standard naming prefix used for environment entries in Java EE applications is `java:comp/env/`.

### Troubleshooting Common Exceptions

The JNDI API relies heavily on checked exceptions. The base exception is `javax.naming.NamingException`. However, the most critical subclass you will encounter is `NameNotFoundException`.

#### NameNotFoundException

This exception is thrown when the `lookup()` method cannot find a binding for the provided name.

- **Common Causes:**
  - **Typo in the Name:** The most frequent culprit. Ensure the lookup string exactly matches the bound string (case-sensitive).
  - **Missing Server Configuration:** The application server (e.g., Tomcat, JBoss) has not been configured to bind the resource. The `context.xml` or `standalone.xml` file might be missing the `<Resource>` definition.
  - **Incorrect Context:** The lookup is being performed in the wrong execution context or before the server has finished initializing the bindings.

## Code Example

Here is how to look up a JDBC `DataSource` from JNDI:

```java
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JndiLookupDemo {

    public Connection getConnection() {
        Connection conn = null;
        try {
            // 1. Obtain the initial context
            Context initialContext = new InitialContext();

            // 2. Perform the lookup (note the target name string)
            // The return type is Object, so an explicit cast is required.
            DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/MyEnterpriseDB");

            // 3. Use the retrieved object
            conn = ds.getConnection();
            System.out.println("Successfully retrieved connection from JNDI DataSource.");

        } catch (javax.naming.NameNotFoundException e) {
            System.err.println("CRITICAL ERROR: The requested JNDI resource was not found.");
            System.err.println("Verify your server's context.xml configuration and check for typos.");

        } catch (NamingException e) {
            System.err.println("A JNDI naming error occurred: " + e.getMessage());
            
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
        
        return conn;
    }
}
```

## Summary

- `InitialContext.lookup("name")` is the primary method for retrieving JNDI resources.
- The returned object must be explicitly cast to the expected type.
- `NameNotFoundException` is the most common diagnostic error, usually indicating a typo or a missing server-level configuration binding.

## Additional Resources

- [Oracle JNDI Documentation - The Lookup Method](https://docs.oracle.com/javase/tutorial/jndi/)
- [Tomcat JNDI Datasource HOW-TO](https://tomcat.apache.org/tomcat-9.0-doc/jndi-datasource-examples-howto.html)
