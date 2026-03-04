# Demo: Spring Cloud Config Server

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** In a Monolith, we have one `application.properties` file. In a Microservice Architecture with 50 distinct services, each deployed across Dev, QA, and Prod environments, managing 150 different properties files on disk is impossible.
2. **The Solution:** Centralized Configuration.
    * We push all properties files (`inventory-service.yml`, `order-service.yml`) to a central Git Repository.
    * We stand up a dedicated `Config Server` that reads that Git Repo.
    * All other microservices, upon starting, immediately call the Config Server over HTTP to ask, "What are my database passwords and settings?"

## Phase 2: The Code (Live Implementation)

**Time:** 15 mins

1. Open `code/ConfigServerApplication.java`.
2. **Walkthrough the Code:**
    * Highlight the `@EnableConfigServer` annotation. It's truly a one-line setup for the Java application.
    * Open `application.yml`.
    * Explain `spring.cloud.config.server.git.uri`. This configuration points the server to a remote GitHub URL containing all the plain-text `.yml` configuration files for the entire company.
3. **Run the App:**
    * Start the server on port `8888`.
    * Navigate in the browser to `localhost:8888/inventory-service/default`. Show how the Config Server instantly fetches the raw JSON properties natively from the Git repo for the specific requested service.
