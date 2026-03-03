# Demo: Hibernate Project Setup and Entity Mapping

## Overview

**Type:** Hybrid (Concept + Code)
**Time:** 45-60 minutes
**Goal:** Create a Spring Boot project with Hibernate/Spring Data JPA, map a simple domain model to the database, and verify the auto-DDL table generation.

---

## Phase 1: The Concept (Whiteboard/Diagram)

**Time:** 10 minutes

1. Open `diagrams/orm-concept.mermaid`.
2. Explain the "Impedance Mismatch" problem:
   - "In Java, we have objects, inheritance, and collections. In SQL, we have tables, rows, and foreign keys."
   - "ORM (Object-Relational Mapping) bridges this gap. Hibernate is the engine that does the translation."
3. Highlight the role of Spring Data JPA: "It provides a repository abstraction over Hibernate so we don't have to write boilerplate CRUD code."

---

## Phase 2: The Code (Live Implementation)

**Time:** 35-50 minutes

### Step 1: Project Setup

- Generate a new Spring Boot project with dependencies: `Spring Web`, `Spring Data JPA`, and `H2 Database`.
- Point out the `spring-boot-starter-data-jpa` dependency in `pom.xml`. "This brings in Hibernate automatically."

### Step 2: Configure the Database

Open `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Show SQL in the console
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Auto-generate schema
spring.jpa.hibernate.ddl-auto=create-drop
```

- "We are using an in-memory H2 database for testing. It will reset every time we restart the app."
- Explain `ddl-auto=create-drop`: "Hibernate will look at our Java classes and generate the SQL `CREATE TABLE` statements for us when the app starts."

### Step 3: Create an Entity

Open `code/Department.java`.

- Add `@Entity` and `@Table`. "This tells Hibernate this class maps to a table."
- Add `@Id` and `@GeneratedValue`. "Every entity needs a primary key."

Open `code/Employee.java`.

- Map basic fields with `@Column`.
- Explain `@ManyToOne` and `@JoinColumn`: "This is how we represent relationships. An Employee belongs to one Department."

### Step 4: Create a Repository

Open `code/EmployeeRepository.java`.

- Create an interface extending `JpaRepository`.
- "With this one interface, Spring gives us `save()`, `findAll()`, `findById()`, and `delete()` methods. We don't have to write any SQL."

### Step 5: Test the Setup

Open `code/SetupRunner.java` (a `CommandLineRunner`).

- "Let's insert some data when the application starts to prove it's working."
- Save a Department, then save two Employees belonging to that department.
- Fetch them back and print them to the console.

### Step 6: Observe the Console Output

Run the application.

1. Point out the `CREATE TABLE` statements. "Hibernate generated these based on our `@Entity` annotations."
2. Point out the `INSERT` statements from the `SetupRunner`.
3. Point out the `SELECT` statements when fetching the data.

---

## Key Talking Points

- `@Entity` is the most important annotation -- it marks a POJO for database mapping.
- `ddl-auto` is great for dev/testing, but never use `update` or `create` in production (use Flyway or Liquibase instead).
- Spring Data JPA uses Hibernate under the hood, but hides the `Session` and `EntityManager` complexity.
- "Tomorrow we will look at how to configure Hibernate without Spring Boot, using `hibernate.cfg.xml`, to understand what Spring is doing for us."
