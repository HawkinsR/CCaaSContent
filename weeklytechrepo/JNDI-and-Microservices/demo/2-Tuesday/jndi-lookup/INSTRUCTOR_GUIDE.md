# Demo: Executing a JNDI Lookup

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** Remind the class of Monday's demo. The Server Administrator successfully bound the `jdbc/MyDataSource` bean into the Tomcat environment.
2. **The Shift in Perspective:** We are now taking on the role of the purely Java Application Developer. Our application has no idea how the database is configured. It only knows that it needs to "look up" a generic connection from the server.

## Phase 2: The Code (Live Implementation)

**Time:** 15 mins

1. Open `code/JndiLookupServlet.java`.
2. Explain that this simulates a standard Java Servlet trying to get a database connection to process an HTTP Request.
3. **Walkthrough the Code:**
    * Highlight the instantiation of `new InitialContext()`. This is opening the server's "Phone Book."
    * Explain the magic string `java:comp/env/`. This is the standard Java prefix for "Look inside my local Component Environment."
    * Show the actual `ctx.lookup("jdbc/MyDataSource")` command.
    * Emphasize the explicit cast to `(DataSource)`. The lookup returns a generic `Object`, so the developer must know what type they are expecting.
    * Point out the `NamingException` block—this is what happens if the Administrator misspelled the resource name yesterday.
4. *Instructor Note:* Explain that this exact boilerplate is what Spring Boot's `@Autowired DataSource` and `application.properties` completely hides from us today.
