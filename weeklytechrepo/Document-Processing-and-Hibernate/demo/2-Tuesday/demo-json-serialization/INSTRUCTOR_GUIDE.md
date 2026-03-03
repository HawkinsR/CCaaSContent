# Demo: JSON Serialization with Jackson and Gson

## Overview

**Type:** Code-focused
**Time:** 30-40 minutes
**Goal:** Demonstrate converting Java objects to/from JSON using both Jackson (Spring's default) and Gson within a Spring Boot REST controller.

---

## Phase 1: The Concept (Brief)

**Time:** 5 minutes

- "Yesterday we marshalled objects to XML. Today we do the same thing with JSON -- the dominant format for REST APIs."
- "Spring Boot includes Jackson automatically. We will also add Gson to compare the two approaches."
- Reference the written content on JSON Processing in Spring.

---

## Phase 2: The Code (Live Implementation)

**Time:** 25-35 minutes

### Step 1: Show Jackson Auto-Configuration

- "Jackson is already on the classpath via `spring-boot-starter-web`. We do not even need to add a dependency."
- Reuse the `Employee` model from the Spring Boot setup demo.

### Step 2: Demonstrate ObjectMapper

Open `code/JacksonDemo.java`:

- Create an `Employee` instance and serialize it: "Notice `writeValueAsString()` -- one line converts our object to JSON."
- Deserialize it back: "`readValue()` takes a JSON string and the target class."
- Show `@JsonProperty` renaming a field and `@JsonIgnore` excluding a field.

### Step 3: Add Gson Dependency

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
</dependency>
```

### Step 4: Demonstrate Gson

Open `code/GsonDemo.java`:

- "Gson's API is simpler: `toJson()` and `fromJson()`. No annotations needed for basic use."
- Compare: "Jackson gives you more control with annotations. Gson is simpler for quick tasks."

### Step 5: Spring REST Controller

Open `code/JsonController.java`:

- "When we return an object from a `@RestController`, Spring uses Jackson to serialize it automatically."
- Show `@RequestBody` deserializing incoming JSON.
- "We never explicitly called ObjectMapper -- Spring did it for us."

### Step 6: Run and Test

1. `GET http://localhost:8080/api/json/jackson` -- returns JSON via Jackson.
2. `POST http://localhost:8080/api/json/parse` -- accepts JSON, returns parsed object.
3. `GET http://localhost:8080/api/json/gson` -- returns JSON via Gson for comparison.

---

## Key Talking Points

- Jackson is the default and recommended choice for Spring Boot.
- `@JsonProperty`, `@JsonIgnore`, and `@JsonFormat` provide fine-grained control.
- Gson is simpler but less integrated with Spring.
- Spring auto-serializes controller return values -- you rarely need to call ObjectMapper directly.
- "The pattern is the same as XML: annotate your model, and the framework handles conversion."
