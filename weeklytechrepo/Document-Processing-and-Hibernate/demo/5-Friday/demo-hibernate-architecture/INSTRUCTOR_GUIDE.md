# Demo: Hibernate Architecture and Service Locator

## Overview

**Type:** Hybrid (Concept-heavy)
**Time:** 45-60 minutes
**Goal:** Diagram and walk through Hibernate's internal architecture, and demonstrate the Service Locator pattern managing a singleton SessionFactory in a multi-DAO application.

---

## Phase 1: The Concept (Whiteboard/Diagram)

**Time:** 20 minutes

1. Open `diagrams/hibernate-layers.mermaid`.
2. Walk through the layered architecture:
   - "Your code (Controllers/Services) talks to DAOs."
   - "The DAO talks to the Hibernate Session."
   - "The Session uses the Transaction API and the First-Level Cache."
   - "The SessionFactory holds the Second-Level Cache."
   - "Hibernate translates everything via JDBC to the Database."
3. **Dirty Checking:** Explain that the single most powerful feature of the Session is that it tracks every object you load. "If you change a setter, Hibernate remembers. When the transaction commits, it generates the UPDATE statement automatically."
4. **Service Locator Pattern:** Explain why a DAO shouldn't create its own Configuration. "It's too expensive. We use a Service Locator (a static registry) to hold the one true SessionFactory."

---

## Phase 2: The Code (Live Implementation)

**Time:** 25-40 minutes

### Step 1: Recap the Service Locator

Open `code/HibernateLocator.java`:

- "We saw a version of this yesterday. This is the Service Locator pattern."
- It has a static initializer block because we only want to bootstrap the `Configuration` once.
- "Any DAO in our application can now call `HibernateLocator.openSession()`."

### Step 2: Implement a DAO using the Locator

Open `code/UserDao.java`:

- Implement `findById(Long id)`: Show opening the session, getting the entity, and closing the session.
- Implement `save(User user)`: Show opening the session, starting a transaction, persisting, committing, and closing.
- "Notice the repeating pattern -- Session open, Transaction begin, do work, commit, close. This is why Spring's `@Transactional` is so popular; it hides this boilerplate."

### Step 3: Demonstrate Dirty Checking

Open `code/DirtyCheckDemo.java`:

- "Let's prove that the First-Level Cache remembers our objects."
- Fetch a `User` entity within a transaction.
- Call a setter method (e.g., `user.setEmail(...)`).
- Do **not** call `session.update()` or `session.persist()`.
- Run the code and watch the console.
- "Look -- an `UPDATE` statement was generated! Because the object was in the 'Persistent' state, the Session detected the change at flush time."

### Step 4: Demonstrate the First-Level Cache

Add to `DirtyCheckDemo.java`:

- Perform `session.get(User.class, 1L)` twice in the same session.
- Point out the console output: "Hibernate only issued one `SELECT` statement. The second `get()` hit the L1 cache."

---

## Key Talking Points

- Hibernate is a layered architecture abstracting JDBC.
- The Service Locator pattern solves the problem of initializing the expensive `SessionFactory` exactly once in standalone apps.
- Dirty checking eliminates the need for manual `update()` calls for persistent entities.
- The First-Level Cache is session-scoped and always enabled -- it guarantees you get the exact same Java object instance for a given database row within that session.
