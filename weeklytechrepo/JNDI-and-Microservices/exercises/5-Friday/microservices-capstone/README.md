# Capstone: The Microservice Ecosystem

## The Scenario

You have spent the entire week learning the isolated components of a distributed system. It is time to prove that you can orchestrate them together on your local machine.

You must launch three independent Java processes that successfully discover and communicate with each other.

## Deliverables

1. **The Infrastructure Layer (Service Registry)**
   - Create a Spring Boot Discovery Server (Eureka Server).
   - Configure it to run on the standard port `8761`.
   - Start the process.

2. **The Client Edge Layer (API Gateway / Client)**
   - Create a bare-minimum Spring Boot microservice named `EDGE-ROUTER`.
   - Add `@EnableDiscoveryClient` and configure it to register with your Eureka server.
   - Start the process (runs on default `8080`).

3. **The Downstream Layer (Business Logic)**
   - Create a third microservice named `INVENTORY-SERVICE`.
   - Ensure it registers with your Eureka server.
   - Configure its `server.port` to `8081` to avoid conflicting with the Edge Router.
   - Start the process.

## Definition of Done

Trainees must submit an architectural diagram (or a screenshot) showing:

- Three active command-line terminals (or IDE Run Configurations) executing simultaneously without throwing Port Conflict Exceptions.
- The Eureka Server Dashboard loaded in a web browser displaying exactly two active instances: `EDGE-ROUTER` and `INVENTORY-SERVICE`.
