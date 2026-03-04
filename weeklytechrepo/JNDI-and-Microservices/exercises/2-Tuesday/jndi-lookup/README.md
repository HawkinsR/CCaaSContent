# Lab: Executing JNDI Lookups and Exception Handling

## The Scenario

Yesterday, the Server Administrator bound two resources to your legacy Tomcat application server:

1. `mail/SupportEmail` (A String)
2. `mainframe/Connection` (A mocked database connection)

Your job as the Application Developer is to write the code that retrieves these configuration variables from the server at runtime.

## Deliverables

1. Navigate to the `starter_code` folder and open `JndiLookupLab.java`.
2. Instantiate the `InitialContext`.
3. Lookup the local environment context (`java:comp/env`).
4. Perform the first lookup for `mail/SupportEmail` and cast it to a `String`. Print its value to the console.
5. Perform the second lookup for `mainframe/Connection` and cast it to `com.enterprise.MainframeRes`. Call its `.connect()` method.
6. **Exception Handling:** Surround your logic with a try/catch block specifically handling the `NamingException`. In the `catch` block, print "A critical JNDI misconfiguration occurred."

## Definition of Done

- Both lookups successfully compile and execute.
- 100% of the lookup logic is protected by a try/catch block capturing `javax.naming.NamingException`.
- The `com.enterprise.MainframeRes` mock class is successfully utilized.
