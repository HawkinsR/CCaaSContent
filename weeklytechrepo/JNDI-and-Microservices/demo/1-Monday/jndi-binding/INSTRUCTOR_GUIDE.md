# Demo: Binding DataSources to the JNDI Tree

## Phase 1: The Concept

**Time:** 10 mins

1. **Context:** Remind trainees that before Spring Boot automatically created database connections for us, Application Servers (like Tomcat, WebSphere, GlassFish) owned the database connections.
2. **The Telephone Book Analogy:** Explain JNDI as a corporate telephone directory. The application asks the server, "Do you have the phone number for the database named `java:comp/env/jdbc/MyDataSource`?"
3. **The Binding Process:** Explain that *Binding* is the act of the Server Administrator placing that entry into the telephone directory.

## Phase 2: The Code (Live Implementation)

**Time:** 20 mins

1. Open `code/EmbeddedTomcatJndi.java`.
2. Explain that in a real enterprise, we would configure an XML file (`context.xml` or `server.xml`). However, to see how it works under the hood, we will *programmatically* define a Tomcat server and bind a dummy DataSource to it.
3. **Walkthrough the Code:**
    * Highlight the instantiation of the `Tomcat` object.
    * Show the creation of the `ContextResource` (this is the physical object being registered).
    * Point out that we are naming it `jdbc/MyDataSource` and telling Tomcat it is of type `javax.sql.DataSource`.
    * Explain the `tomcat.getServer().getGlobalNamingResources().addResource(resource)` line as the actual JNDI binding action.
4. Run the application. Show the console output confirming Tomcat has started and bound the resource.
5. *Instructor Note:* Tell them that tomorrow, we will write the code that acts as the completely separate application asking Tomcat for this exact resource.
