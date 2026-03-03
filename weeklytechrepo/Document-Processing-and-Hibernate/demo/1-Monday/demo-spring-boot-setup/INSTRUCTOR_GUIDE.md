# Demo: Spring Boot Project Setup

## Overview

**Type:** Hybrid (Concept + Code)
**Time:** 45-60 minutes
**Goal:** Scaffold a Spring Boot project from scratch, walk through the application context, key annotations, and the request lifecycle.

---

## Phase 1: The Concept (Whiteboard/Diagram)

**Time:** 15 minutes

1. Open `diagrams/spring-boot-architecture.mermaid`.
2. Walk through the diagram top-to-bottom:
   - The HTTP request arrives at the **DispatcherServlet**.
   - Spring's **ApplicationContext** scans for `@Controller` / `@RestController` beans.
   - The controller delegates to a `@Service`, which calls a `@Repository`.
   - The response flows back.
3. **Discussion:** Ask the class: "Why do we separate concerns into Controller, Service, and Repository layers? What happens if we put everything in the controller?"
4. Highlight that **Dependency Injection** is what connects these layers -- Spring creates the beans and wires them together automatically.

---

## Phase 2: The Code (Live Implementation)

**Time:** 30-45 minutes

### Step 1: Generate the Project

1. Navigate to [Spring Initializr](https://start.spring.io/) or use the IDE's built-in generator.
2. Configure the project:
   - **Group:** `com.example`
   - **Artifact:** `demo-app`
   - **Dependencies:** Spring Web
3. Open the generated project in the IDE.

### Step 2: Walk Through the Project Structure

Open the project explorer and point out each directory:

```
demo-app/
  src/main/java/com/example/demoapp/
    DemoAppApplication.java         <-- Entry point
  src/main/resources/
    application.properties          <-- Configuration
  pom.xml                           <-- Dependencies
```

Open `DemoAppApplication.java` and explain:

```java
@SpringBootApplication  // = @Configuration + @EnableAutoConfiguration + @ComponentScan
public class DemoAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoAppApplication.class, args);
    }
}
```

- "This single annotation does three things. Let me show you what each one means."
- Reference the written content definition of `@SpringBootApplication`.

### Step 3: Create a Model

Open `code/Employee.java` and live-code:

```java
package com.example.demoapp.model;

public class Employee {
    private Long id;
    private String name;
    private String department;

    public Employee() {}

    public Employee(Long id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    // Generate getters and setters using IDE shortcut
}
```

### Step 4: Create a Service with DI

Open `code/EmployeeService.java`:

```java
package com.example.demoapp.service;

import com.example.demoapp.model.Employee;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    public List<Employee> getAllEmployees() {
        return Arrays.asList(
            new Employee(1L, "Alice", "Engineering"),
            new Employee(2L, "Bob", "Marketing"),
            new Employee(3L, "Charlie", "Engineering")
        );
    }
}
```

- "Notice the `@Service` annotation. This tells Spring to create a bean of this class and manage it."

### Step 5: Create a REST Controller

Open `code/EmployeeController.java`:

```java
package com.example.demoapp.controller;

import com.example.demoapp.model.Employee;
import com.example.demoapp.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    // Constructor Injection -- the recommended approach
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }
}
```

- "I did not create the service with `new`. Spring injected it through the constructor. This is constructor injection."
- Point back to the architecture diagram: "This is the Controller calling the Service, just like in our diagram."

### Step 6: Run and Test

1. Run the application: `mvn spring-boot:run` or use the IDE's Run button.
2. Open browser or Postman: `GET http://localhost:8080/api/employees`
3. Show the JSON response.
4. "Notice we did not configure Jackson or JSON serialization. Spring Boot auto-configured that because `spring-boot-starter-web` is on the classpath."

### Step 7: Show Configuration

Open `application.properties` and add:

```properties
server.port=9090
```

Restart and show that the app now runs on port 9090.

- "Everything in Spring Boot is configurable. This file is the central place for application settings."

---

## Key Talking Points

- `@SpringBootApplication` bootstraps the entire context.
- Constructor injection keeps code clean and testable.
- Auto-configuration removes boilerplate (embedded server, Jackson, etc.).
- The layered architecture (Controller, Service, Repository) is a pattern you will use all week and throughout your career.
- "This Spring Boot foundation is what we will build on for the rest of the week -- XML processing, JSON handling, JMS, and Hibernate all plug into this same structure."
