# Exercise: Criteria Queries & Native SQL (Collaborative Pair Programming)

## Overview

**Mode:** Collaborative Pair Programming (Driver/Navigator)
**Time Estimate:** 1.5 - 2 Hours

**The Thursday Protocol:** Continue in your pairs. Switch your Driver/Navigator roles from the previous exercise.

**Scenario:** Your company's BI (Business Intelligence) reporting tool is too slow. The lead architect has tasked you with writing three different styles of queries against the database so the DBA team can analyze their performance and execution plans.

## Your Goal

Given a pre-existing schema and populated dataset, you must write the same data retrieval logic three ways: using the Criteria API, using Native SQL, and using Named Queries.

---

## Starter Code Setup

Navigate to `/starter_code`. This is a Spring Boot project. The `Product` and `Category` entities are already written. `DataLoader.java` ensures the database is pre-populated with 50 randomized products across 5 categories when the application starts.

---

## Core Tasks

Your goal is to implement three methods in the `ProductSearchService.java` stub.

### Task 1: Criteria API

Implement `findProductsInPriceRangeCriteria(double min, double max)`.

- **Constraint:** You must use `session.getCriteriaBuilder()`.
- **Constraint:** No strings representing SQL/HQL are allowed (except property names).
- Return a list of all products where the price falls between the parameters.

### Task 2: Native SQL

Implement `findAveragePriceByCategoryNative(String categoryName)`.

- **Constraint:** Use `session.createNativeQuery()`.
- **Constraint:** You must write raw, dialect-specific SQL (e.g., `SELECT AVG(p.price) FROM products p JOIN categories c...`).
- Execute the query and return the `Double` value.

### Task 3: Named Queries

Implement `findProductsWithLowInventoryNamed(int threshold)`.

1. Open the `Product` entity class.
2. Add a `@NamedQuery` annotation at the top of the class. Name it `"Product.findLowStock"`.
3. Write the HQL statement inside the annotation to find products where `stock_count < :threshold`.
4. Go back to `ProductSearchService` and execute `session.createNamedQuery("Product.findLowStock")`.

---

## Definition of Done

1. **Validation Tests:** We have provided `ProductSearchServiceTest.java`.
2. Run the test suite: `mvn test` or via your IDE.
3. The exercise is complete ONLY when all tests pass, proving your three different query mechanics return the correct datasets.
