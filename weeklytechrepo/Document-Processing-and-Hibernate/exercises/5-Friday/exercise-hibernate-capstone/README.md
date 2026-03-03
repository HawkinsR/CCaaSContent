# Week 1 Capstone Challenge: The "CCaaS Orchestrator"

## Overview

**Mode:** Hybrid (Design + Code Lab)
**Time Estimate:** 3 - 4 Hours

**Scenario:** You have been assigned to prototype the core backend for the Contact Center system. You need to pull all the concepts from this week together into a single cohesive microservice flow.

## Your Goal

Design the ERD and component architecture for the Orchestrator, then implement a slice of it demonstrating Spring Boot, JPA/Hibernate, and ActiveMQ.

---

## Part 1: The Design (Conceptual)

Navigate to the `/templates` folder.

### The Requirements

When a caller dials the contact center (`CallEvent` object):

1. The system creates a `CallRecord` entity in the database representing the inbound call.
2. The system checks a `CustomerProfile` table to see if the caller's phone number exists.
3. If the profile exists, the `CallRecord` is linked to the `CustomerProfile`.
4. The system marshals the `CallRecord` object into JSON.
5. The system drops the JSON message onto an internal JMS Queue named `routing-decision-queue`.

### Your Task

1. Open `erd.mermaid` and define the tables, columns, and relationships (One-to-Many, Many-to-One) for `CustomerProfile`, `CallRecord`, and any required enum lookup tables.
2. Write a brief architectural flow in `architecture.md` answering:
   - Which component handles the HTTP request?
   - Which component opens the database transaction?
   - What happens if the ActiveMQ broker is down? Should the database commit roll back?

---

## Part 2: The Implementation (Code Lab)

Navigate to the `/starter_code` folder.

### Your Task

Using the ERD you just designed, build out the `CallOrchestratorService` class.

1. **The Entities:** Map your `CustomerProfile` and `CallRecord` objects using `@Entity`, `@Id`, `@Column`, and `@ManyToOne` annotations.
2. **The ACID Transaction:** The method `processInboundCall(CallEvent event)` **MUST** be annotated with `@Transactional`.
3. **The Work:**
   - Lookup the profile using a Spring Data JPA Repository query method (e.g., `findByPhoneNumber`).
   - Create and persist the `CallRecord`. (If profile is found, link them).
   - Use Jackson's `ObjectMapper` to convert the saved `CallRecord` into a JSON string.
   - Use `JmsTemplate` to send the JSON to `routing-decision-queue`.
4. **The Rollback:** Simulate a failure right after you save to the database, but before the JMS message sends by typing `if (true) throw new RuntimeException("Simulated Broker Crash!");`
   - Verify that the `CallRecord` IS NOT saved in the database if the JMS message fails to send by inspecting your H2 console.

---

## Definition of Done

1. Your `erd.mermaid` diagram correctly renders a database relationship between User profiles and their associated call logs.
2. You successfully pass the `InboundCallTest` suite, proving that your persistence layer rolls back perfectly during a simulated network failure.
