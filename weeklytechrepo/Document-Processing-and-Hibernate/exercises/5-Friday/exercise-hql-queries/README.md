# Exercise: Advanced HQL & Projections

## Overview

**Mode:** Implementation (Code Lab)
**Time Estimate:** 2 Hours

**Scenario:** You are writing the backend for a complex administrative dashboard. The UI needs statistical data and aggregated lists, not just simple entity lookups. Instead of pulling down thousands of `Order` rows and calculating the sum in Java, you need to execute these calculations at the database level using HQL.

## Your Goal

Write advanced HQL queries featuring joins, aggregations, parameterized searches, and projection DTOs (Data Transfer Objects).

---

## Starter Code Setup

Navigate to `/starter_code`. This is a Spring Data JPA project. The `Employee`, `Department`, and `Project` entities are mapped with Many-to-One and Many-to-Many relationships. `DataSeeder.java` populates thousands of rows on startup.

---

## Core Tasks

### Task 1: Inner Joins and Filtering

Write the method `findEmployeesByDepartmentName(String deptName)`.

- Use an explicit `JOIN` in your HQL to navigate from `Employee` to `Department`.
- *Wait! Do I really need an explicit join?* Write a second method `findEmployeesByDepartmentNameImplicit(String deptName)` using dot notation (`e.department.name = :deptName`). Compare the generated SQL in the console.

### Task 2: Addressing the N+1 Query Problem

1. The `Department` entity has a `@OneToMany` list of `employees`. By default, this is dynamically loaded (Lazy).
2. Write the method `getDepartment(Long id)`. Use the standard Spring Data JPA method `departmentRepository.findById(id)`.
3. In your controller, return that Department object. Watch your console. Every time Jackson tries to serialize the `employees` JSON array, Hibernate fires another SELECT query! This is N+1.
4. **The Fix:** Write a custom `@Query` on the `DepartmentRepository` containing this HQL: `SELECT d FROM Department d JOIN FETCH d.employees WHERE d.id = :id`.
5. Call your custom method instead and verify the N+1 problem is solved.

### Task 3: Aggregations

1. Write `getTotalPayrollByDepartment(Long deptId)`. Use `SUM(e.salary)` in your HQL. Give the method a return type of `Double`.
2. Write `countEmployeesInProject(Long projectId)`. Navigate the `@ManyToMany` join table in HQL (`e.projects p`).

### Task 4: DTO Projections

1. The dashboard needs a list of names and salaries, but `Employee` objects are too heavy (they contain huge biography fields).
2. Look at the `EmployeeSummaryDto` class provided.
3. Write an HQL query that instantiates this object directly inside the query string: `SELECT new com.revature.dto.EmployeeSummaryDto(e.name, e.salary) FROM Employee e...`
4. Return a `List<EmployeeSummaryDto>`.

---

## Definition of Done

1. **Tests Passed:** The provided `HqlQueriesTest.java` file asserts all required methods return the correct values or lists.
2. **Visual Inspection:** Verify in the console that `JOIN FETCH` generates exactly one `SELECT` statement.
