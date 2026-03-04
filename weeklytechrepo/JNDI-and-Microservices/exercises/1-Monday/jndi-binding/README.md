# Lab: JNDI Environment Variables and Binding

## The Scenario

You are a Server Administrator configuring a legacy Tomcat server. The application developers have requested two things to be made globally available via JNDI:

1. A standard string environment variable stating the company's support email address.
2. A custom connection resource to an obscure Mainframe system.

You must programmatically configure an embedded Tomcat server, enable JNDI naming, and bind these two resources so they are ready for the developers tomorrow.

## Deliverables

1. Navigate to the `starter_code` folder and open `JndiLab.java`.
2. Enable JNDI naming on the provided Tomcat instance.
3. Create a `ContextResource` named `mail/SupportEmail` of type `java.lang.String`. Provide it a property `value` equal to `support@enterprise.com`.
4. Create a `ContextResource` named `mainframe/Connection` of type `com.enterprise.MainframeRes`. Provide it a `port` property of `8081`.
5. Add both resources to the global naming context of the server.
6. Start Tomcat and verify the console shows no errors.

## Definition of Done

- JNDI is explicitly enabled on the Tomcat object.
- Both string and custom resources are defined and bound to `getGlobalNamingResources()`.
- The application executes and pauses via `tomcat.getServer().await()` without throwing exceptions.
