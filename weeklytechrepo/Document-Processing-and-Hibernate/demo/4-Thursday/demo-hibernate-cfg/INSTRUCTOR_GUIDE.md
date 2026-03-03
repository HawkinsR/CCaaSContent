# Demo: Hibernate Configuration (hibernate.cfg.xml)

## Overview

**Type:** Hybrid (Concept + Code)
**Time:** 45-60 minutes
**Goal:** Configure Hibernate as a standalone technology (without Spring Boot) using `hibernate.cfg.xml`. Walk through the session management lifecycle.

---

## Phase 1: The Concept (Whiteboard/Diagram)

**Time:** 10 minutes

1. Open `diagrams/hibernate-bootstrap.mermaid`.
2. Explain the manual bootstrapping process:
   - "Spring Boot does this for us automatically, but in a raw Java application, we have to do it ourselves."
   - The **Configuration** object reads the XML file.
   - It builds the **SessionFactory** (a heavy, application-wide object).
   - The SessionFactory creates **Session** objects (lightweight, single request/transaction).
3. "The Session is our connection to the database. We open it, begin a transaction, do work, commit, and close it."

---

## Phase 2: The Code (Live Implementation)

**Time:** 35-50 minutes

### Step 1: Create the Project

- Generate a basic Maven project (not Spring Boot).
- Add `hibernate-core` and database driver (e.g., MySQL or H2) to `pom.xml`.

### Step 2: Create hibernate.cfg.xml

Open `code/hibernate.cfg.xml` in `src/main/resources`:

- Explain each section: connection properties, dialect, show_sql, and hbm2ddl.
- "The dialect tells Hibernate exactly what flavor of SQL to generate."
- Point out the `<mapping class="..."/>` tags at the bottom. "We must explicitly register our entities here since we don't have Spring to scan for them."

### Step 3: Create the HibernateUtil Helper

Open `code/HibernateUtil.java`:

- Walk through the static initializer block.
- "We only want ONE SessionFactory for the whole applications. It is expensive to build."
- "This singleton pattern gives us a global point of access to it."

### Step 4: Create an Entity

- Reuse a simple `Product` entity from previous examples (or write a quick one with `@Entity`, `@Id`, etc.).
- Ensure it is registered in the XML configuration.

### Step 5: Execute the Lifecycle

Open `code/Main.java`:

- Write the code live:

```java
Session session = HibernateUtil.getSessionFactory().openSession();
Transaction tx = null;
try {
    tx = session.beginTransaction();
    
    // Save a new product
    Product p = new Product("Laptop", 1200.0);
    session.persist(p);
    
    tx.commit();
} catch (Exception e) {
    if (tx != null) tx.rollback();
    e.printStackTrace();
} finally {
    session.close();
}
```

- Explicitly explain the Try-Catch-Finally block. "Transactions must be committed or rolled back. Sessions must be closed to release the database connection."
- Run the code and show the generated SQL in the console.

### Step 6: Demonstrate Retrieving Data

- Add a block to open a new session and `get()` the product.
- "Notice we don't always need a transaction just to read data, but it's a best practice, especially with lazy loading."

---

## Key Talking Points

- In standalone applications, `hibernate.cfg.xml` is the center of the configuration universe.
- The `SessionFactory` is thread-safe and heavy; create it once. The `Session` is not thread-safe; create one per request/unit of work.
- Explicit transaction management (begin, commit, rollback) is required without Spring's `@Transactional`.
- "Understanding this manual setup makes you appreciate Spring Data JPA, but also gives you the foundation needed to debug enterprise legacy codebases."
