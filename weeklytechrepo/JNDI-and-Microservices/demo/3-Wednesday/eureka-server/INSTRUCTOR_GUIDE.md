# Demo: Standing up a Eureka Server

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** Remember JNDI from Monday and Tuesday? We had hardcoded references to resources via `java:comp/env`. We also had monolithic applications on a single server. In Microservices, we might have 50 instances of the "Inventory Service" running on random IP addresses.
2. **The Solution:** We need a dynamic Phone Book. Spring Cloud Netflix Eureka acts as this central Service Registry.

## Phase 2: The Code (Live Implementation)

**Time:** 15 mins

1. Open `code/EurekaServerApplication.java`.
2. **Walkthrough the Code:**
    * Highlight the simplicity: We only need to add `@EnableEurekaServer` to a standard Spring Boot application.
    * Open `application.yml` (Review the required properties).
    * Explain `eureka.client.register-with-eureka=false` and `fetch-registry=false`. Why? Because this application *is* the server; it doesn't need to register with itself.
3. **Run the App:**
    * Start the Spring Boot application (Default port 8761).
    * Open a web browser and navigate to `http://localhost:8761`.
    * Show the trainees the Eureka Dashboard. Point out that there are currently "No instances available" because no clients have registered yet.
