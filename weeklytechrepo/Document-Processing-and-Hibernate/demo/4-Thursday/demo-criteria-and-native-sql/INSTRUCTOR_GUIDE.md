# Demo: Criteria API, Native SQL, and Named Queries

## Overview

**Type:** Code-focused
**Time:** 30-40 minutes
**Goal:** Demonstrate the three primary ways to query data in Hibernate (excluding basic CRUD and HQL): Criteria API, Native SQL, and Named Queries. Compare their use cases.

---

## Phase 1: The Concept (Brief)

**Time:** 5 minutes

- "We know how to save and fetch by ID. But how do we search for data?"
- "Today we cover three specialized querying methods:"
  1. **Criteria API:** Type-safe, programmatic, object-oriented. Good for dynamic queries (like search filters).
  2. **Native SQL:** Raw database-specific SQL. Good for database-specific features or extreme optimization.
  3. **Named Queries:** Pre-compiled queries attached to the entity. Good for reusable, validated queries.

---

## Phase 2: The Code (Live Implementation)

**Time:** 25-35 minutes

### Step 1: Create the Entity and Data

- Use a `Book` entity with `title`, `author`, and `price`.
- Insert a few rows of sample data in `SetupRunner.java`.

### Step 2: Demonstrate the Criteria API

Open `code/QueryDemoService.java` and write `findBooksByAuthorCriteria()`:

```java
public List<Book> findBooksByAuthorCriteria(String authorName) {
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Book> query = cb.createQuery(Book.class);
    Root<Book> root = query.from(Book.class);

    query.select(root)
         .where(cb.equal(root.get("author"), authorName))
         .orderBy(cb.asc(root.get("title")));

    return session.createQuery(query).getResultList();
}
```

- "Notice there are no SQL strings here. If I misspell 'author', it fails at runtime, but there are tools to make it fail at compile time (Metamodel)."
- "This is very verbose. Why use it? Because it's easy to build dynamically: `if (author != null) add where clause...`"

### Step 3: Demonstrate Native SQL

Write `findExpensiveBooksNative()`:

```java
public List<Book> findExpensiveBooksNative(double minPrice) {
    return session.createNativeQuery(
            "SELECT * FROM books WHERE price > :minParam", Book.class)
            .setParameter("minParam", minPrice)
            .getResultList();
}
```

- "This is exactly what you would type into pgAdmin or MySQL Workbench."
- "The downside: if you switch databases from MySQL to Oracle, this query might break if you used MySQL-specific syntax."

### Step 4: Demonstrate Named Queries

Open `code/Book.java` and add the annotation:

```java
@Entity
@NamedQuery(
    name = "Book.findByTitleKeyword",
    query = "SELECT b FROM Book b WHERE b.title LIKE :keyword"
)
public class Book { ... }
```

Go back to `QueryDemoService.java` and write the calling method:

```java
public List<Book> findByTitleNamed(String keyword) {
    return session.createNamedQuery("Book.findByTitleKeyword", Book.class)
            .setParameter("keyword", "%" + keyword + "%")
            .getResultList();
}
```

- "This query is HQL, not SQL. It is parsed when Hibernate starts. If there is a syntax error, the application crashes immediately on startup -- fail fast!"

### Step 5: Run and Compare

- Execute all three methods from the `Runner` and show the console output.
- Show the generated SQL in the console for the Criteria API and Named Query to prove Hibernate translates them.

---

## Key Talking Points

- Use **Criteria API** when the query structure depends on runtime conditions (e.g., optional search filters on a web page).
- Use **Native SQL** only as a last resort, when HQL/Criteria cannot express the database-specific feature you need.
- Use **Named Queries** for static, frequently used queries that should be validated at application startup.
