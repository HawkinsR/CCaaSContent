# Application Integration: Spring Data Cassandra

## Learning Objectives

- Define the role of Object Mapping in a Java application communicating with a NoSQL database.
- Configure a Spring Boot application to connect to a local Cassandra cluster.
- Map POJO models to CQL Tables using annotations like `@Table` and `@PrimaryKey`.
- Implement simple Create, Read, Update, and Delete operations using `CassandraRepository`.

## Why This Matters

CQL is required for database administrators and testing, but enterprise software rarely connects directly via command-line strings. Modern Java applications use frameworks to construct these strings automatically, significantly reducing boilerplate code and protecting against errors like SQL Injection. Reusing the concepts originally learned with Hibernate (Week 1), **Spring Data Cassandra** dramatically simplifies the creation of object-to-database mapping layers for NoSQL wide-column stores.

## The Concept

### Object Mapping

When using Hibernate with a relational database, you mapped Java classes to normalized SQL tables using `@Entity`.
Because Cassandra is heavily denormalized and entirely lacks relationships (no `@OneToMany`), the mapping approach is simpler but demands strict adherence to the Primary Key structure. Spring Data Cassandra translates your Java objects into CQL `INSERT`, `UPDATE`, and `SELECT` statements under the hood.

### Configuring Spring Boot

To use Spring Data Cassandra, you add the `spring-boot-starter-data-cassandra` dependency to your `pom.xml`.
You must then provide your cluster configuration to the `application.properties` file:

- **Contact Points:** The IP address of at least one healthy Cassandra node (e.g., `127.0.0.1` for local Docker setups).
- **Port:** Default is `9042`.
- **Keyspace:** The name of the specific Keyspace (database) to connect to.

*(Note: Spring will automatically discover the rest of the nodes in the cluster using the gossip protocol after establishing that initial connection.)*

### Annotations for Mapping

Spring Data Cassandra uses its own specific annotations, separate from the JPA annotations used in Week 1.

1. **`@Table`**: Declares that a specific Java class maps directly to a backing Cassandra table.
2. **`@PrimaryKey`**: Marks a primitive field (like a `UUID`) as the Partition Key.
    - If your primary key is a composite (a Partition Key + multiple Clustering Columns), you must create a separate dedicated Java Class annotated with `@PrimaryKeyClass` to model that complex key, and embed it using **`@PrimaryKey`**.
3. **`@Column`**: Maps a Java field to a specific CQL column (optional, Spring infers matching names automatically).

### The Repository Pattern

Just like `JpaRepository`, Spring Data Cassandra provides interfaces to abstract away the Database drivers.
By extending `CassandraRepository<ModelClass, KeyType>`, your application inherits pre-built methods:

- `save(entity)`: Performs a Cassandra "Upsert" query.
- `findById(uuid)`: Performs a highly optimized point-read using the Partition Key.
- `delete(entity)`: Issues a tombstone deletion request to the cluster.

## Code Example

```java
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

// --- MODEL ---
// Maps to the "users" table in the active keyspace
@Table("users")
public class User {

    // Maps the user_id to the Primary Key (Partition Key)
    @PrimaryKey
    private UUID userId;
    
    // Automatically mapped to first_name
    private String firstName;
    
    // Automatically mapped to age
    private Integer age;

    // Constructors, Getters, Setters omitted for brevity
}


// --- REPOSITORY ---
@Repository
public interface UserRepository extends CassandraRepository<User, UUID> {
    // Basic CRUD operations (save, findById, delete) are automatically inherited.
    // NOTE: You cannot simply write findByFirstName(String name) because
    // querying on a non-primary key requires ALLOW FILTERING, which Spring
    // intentionally makes difficult to execute to prevent cluster degradation.
}


// --- application.properties ---
// spring.data.cassandra.contact-points=127.0.0.1
// spring.data.cassandra.port=9042
// spring.data.cassandra.keyspace-name=e_commerce
// spring.data.cassandra.local-datacenter=datacenter1
```

## Summary

- **Spring Data Cassandra** maps Java POJOs directly to CQL tables, abstracting driver connection logic.
- Configurations in `application.properties` dictate the contact point nodes and the target Keyspace.
- Use `@Table` and `@PrimaryKey` to instruct Spring on how to generate the corresponding CQL UPSERT and SELECT statements.
- The `CassandraRepository` provides standard CRUD methods optimized for distributed wide-column architectures.

## Additional Resources

- [Spring Data Cassandra Reference Documentation](https://docs.spring.io/spring-data/cassandra/reference/)
- [Baeldung: Introduction to Spring Data Cassandra](https://www.baeldung.com/spring-data-cassandra-tutorial)
