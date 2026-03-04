# Lab: The Service Registry Ecosystem

## The Scenario

Your company has decided to transition from a Monolith to Microservices. You must prove the viability of a dynamic service registry before production deployment. You need to stand up a standalone Discovery Server and launch two distinctly independent instances of an "Account Service" to prove Eureka can load-balance internally.

## Deliverables

1. **Server Setup:**
   * Navigate to `starter_code/server`. Add the correct class-level annotation to `RegistryApplication.java`.
   * Configure `application.yml` so it listens on port `8761` and does not attempt to register with itself.
2. **Client Setup:**
   * Navigate to `starter_code/client`. Add the discovery annotation to `AccountServiceApplication.java`.
   * Configure `application.yml`. Set the application name to `ACCOUNT-SERVICE` and point the `defaultZone` to your local Eureka server.
3. **Execution:**
   * Start the Server application.
   * Start the Client application.
   * *Critical Step:* Modify the Client's `application.yml` to run on port `8082` instead of `8081`. Start it *again* to launch a second instance.
4. **Verification:** Navigate to `http://localhost:8761`. You should see one Application (`ACCOUNT-SERVICE`) with two status entries (e.g., `UP (2) - laptop:account-service:8081, laptop:account-service:8082`).

## Definition of Done

- A functional central Service Registry is accessible via browser on port `8761`.
* Two separate JVM processes of the AccountService are running simultaneously and successfully registered with the Eureka Server.
