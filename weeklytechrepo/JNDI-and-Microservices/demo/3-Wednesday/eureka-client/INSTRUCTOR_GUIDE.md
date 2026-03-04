# Demo: Registering a Eureka Client

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** The Registry is running (our `eureka-server` from the previous demo). Now we need a microservice (e.g., the `InventoryService`) to advertise its existence to the server.
2. **The Heartbeat:** Explain that when a client starts, it sends an HTTP POST request to the Eureka server saying "I am available." It then sends periodic "Heartbeats" every 30 seconds to prove it hasn't crashed.

## Phase 2: The Code (Live Implementation)

**Time:** 15 mins

1. Open `code/EurekaClientApplication.java`.
2. **Walkthrough the Code:**
    * Highlight the `@EnableDiscoveryClient` annotation (or `@EnableEurekaClient`). Explain this tells Spring Boot to look for the Eureka dependency.
    * Open `application.yml`.
    * Explain `spring.application.name=inventory-service`. This is exactly how other services will find it in the phone book!
    * Point out `eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/`. That's where the registry is running.
3. **Run the App:**
    * Start the application on port `8080`.
    * Go back to the browser: `http://localhost:8761`.
    * Refresh. Show the trainees that `INVENTORY-SERVICE` is now formally listed under "Instances currently registered with Eureka."
