# Lab: Spring Boot Cassandra API

## The Scenario

Your development team is building a decentralized social media clone. The Architects have dictated that "User Posts" must be stored in Apache Cassandra due to the anticipated extreme write operations (millions of users posting simultaneously).

You must write the Spring Boot REST API that the Frontend React team will call to create and retrieve these posts.

## Prerequisites

- You must have your local Cassandra Docker Node running from Monday.
- You must have the `store` keyspace created.

## Deliverables

1. Navigate to the `starter_code` directory. The basic architecture has been scaffolded for you.
2. Open `application.properties` and provide the correct Cassandra coordinates to attach to your local Docker container.
3. Open `Post.java`. Add the necessary `@Table` and `@PrimaryKey` annotations.
4. Open `PostRepository.java` and make it extend `CassandraRepository`.
5. Open `PostController.java`.
    - Create a `@PostMapping` that accepts a `Post` object in the Request Body, generates a new `UUID.randomUUID()` for its ID, saves it via the repository, and returns it.
    - Create a `@GetMapping` that uses the repository to return a list of all posts.
6. **Testing:** Boot up the Application. Use Postman or `cURL` to send a POST request with JSON to the port (e.g., `8080/posts`), then send a GET request to verify Cassandra persisted the data.

## Definition of Done

- The Spring Boot application starts without throwing connection exceptions to Cassandra.
- Sending a POST request successfully translates the JSON payload into a CQL `INSERT` and persists it to the Docker node.
- Sending a GET request successfully pulls the list back from Cassandra.
