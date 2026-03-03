# JNDI Overview

## Learning Objectives

- Define what the Java Naming and Directory Interface (JNDI) is.
- Understand the core concepts of JNDI: Name, Context, and Bindings.
- Learn the basics of how to bind objects to a JNDI directory tree.

## Why This Matters

As you transition toward building distributed microservices, it is critical to understand how enterprise Java applications historically solved the problem of "finding resources." Before modern dependency injection frameworks (like Spring) and service registries (like Eureka) existed, JNDI was the standard API used to locate databases, message queues, and other services across a network. Understanding JNDI provides essential historical context and is still required when deploying applications to traditional Java EE application servers like Tomcat, WildFly, or WebLogic.

## The Concept

### What is JNDI?

The Java Naming and Directory Interface (JNDI) is an API specified by Java that provides naming and directory functionality to applications. It allows a Java application to discover and look up data and objects via a generic, standard interface, regardless of the underlying implementation.

Think of JNDI like a phone book or the DNS system of the internet, but specifically for Java components. Instead of hardcoding the IP address of a database into your Java code, you ask JNDI for the "CustomerDatabase", and JNDI returns the connection details.

### Core Terminology

1. **Name:** The identifier used to register and look up an object. This is typically represented as a hierarchical string, often using a URL-like syntax (e.g., `java:comp/env/jdbc/myDataSource`).
2. **Context:** The core interface in JNDI (`javax.naming.Context`). A Context represents a set of name-to-object bindings. It is the starting point (the root) for resolving names. Every JNDI lookup starts by obtaining an `InitialContext`.
3. **Binding:** The association between a Name and an Object. When an administrator configures an application server, they "bind" a DataSource object to a specific name.

### The Binding Process

Binding is typically done by the application server (the container) when it starts up, reading from configuration files (like Tomcat's `context.xml`). However, it can also be done programmatically.

To bind an object programmatically, you obtain an `InitialContext` and call the `bind()` method, providing a name and the object itself. Once bound, any other component in the application that has access to that Context can retrieve the object.

## Code Example

While usually handled by server configuration, here is how you might programmatically bind an object to a JNDI context using the Java API:

```java
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JndiBindingDemo {

    public static void main(String[] args) {
        try {
            // 1. Obtain the initial context (the root of the naming directory)
            Context ctx = new InitialContext();

            // 2. Create an object to bind
            String databaseUrl = "jdbc:postgresql://localhost:5432/enterprise_db";

            // 3. Bind the object to a specific name in the directory
            ctx.bind("java:comp/env/config/DatabaseUrl", databaseUrl);

            System.out.println("Object successfully bound to JNDI.");

        } catch (NamingException e) {
            System.err.println("Failed to bind object: " + e.getMessage());
        }
    }
}
```

## Summary

- JNDI is the standard Java API for looking up resources like database connections and message queues.
- A **Name** is the identifier, an **Object** is the resource, and a **Binding** is the relationship between them.
- The **Context** is the environment where these bindings live.
- While modern Spring Boot applications often bypass JNDI in favor of auto-configuration and externalized properties, it remains a foundational enterprise technology.

## Additional Resources

- [Oracle Official JNDI Tutorial](https://docs.oracle.com/javase/tutorial/jndi/)
- [Baeldung: Guide to JNDI](https://www.baeldung.com/jndi)
