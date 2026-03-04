# Demo: Auto-Populating Cassandra via CommandLineRunner

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** The API needs data to function. We could manually type `INSERT INTO` statements into `cqlsh`, but that doesn't scale for development teams.
2. **Data Population:** We can use Spring Boot's `CommandLineRunner` to inject mock data immediately after the application finishes initializing, using our brand new `ProductRepository`.

## Phase 2: The Code (Live Implementation)

**Time:** 10 mins

1. Open `code/NoSqlDemoApplication.java`.
2. **Walkthrough the Code:**
    * Show the main Spring Boot application class.
    * Highlight the `@Bean CommandLineRunner`. Explain that Spring executes this bean exactly once during startup.
    * Show the injection of the `ProductRepository`.
    * Walk through creating `Product` Java objects and calling `repository.save()`, which Spring translates into a CQL `INSERT` behind the scenes.
    * Show the `repository.findAll()` which validates the data.
3. **Run the App:**
    * (Assuming a full Spring Boot project was set up by the instructor) Run the application.
    * Show the console logs proving the beans were saved and retrieved from the local Docker node.
