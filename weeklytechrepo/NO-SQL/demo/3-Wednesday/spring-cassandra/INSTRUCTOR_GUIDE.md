# Demo: Spring Data Cassandra Setup

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** We know how to create tables and insert data using the bare-metal `cqlsh` prompt. However, our Enterprise web applications are written in Java, not raw CQL.
2. **Spring Data:** Introduce `spring-boot-starter-data-cassandra`. Explain that it behaves almost exactly like Spring Data JPA (Hibernate), providing abstractions over the raw database connection.
3. **The Annotations:** Show how `@Entity` and `@Id` from JPA are replaced by `@Table` and `@PrimaryKey` for Cassandra.

## Phase 2: The Code (Live Implementation)

**Time:** 15 mins

1. Open `code/application.properties`.
2. **Database Connection:** Walk through configuring Spring Boot to look for the local Docker Cassandra node. Explain the `contact-points`, `keyspace-name`, and the critical `schema-action=create-if-not-exists`. Let Spring Boot build the table for you!
3. Open `code/Product.java` and `code/ProductRepository.java`.
4. **Walkthrough the Code:**
    * Show the `@Table("products")` annotation mapping the POJO to the CQL table.
    * Show `@PrimaryKey`. Note that this signifies the combination of the Partition Key *and* the Clustering Column. (Complex multi-column keys require a `@PrimaryKeyClass`).
    * Show the `CassandraRepository` interface. It extends `CrudRepository` just like JPA, giving us free `.save()`, `.findAll()`, and `.findById()` methods.
