# Demo: Advanced HQL and Transactions

## Overview

**Type:** Code-focused
**Time:** 30-40 minutes
**Goal:** Demonstrate HQL (Hibernate Query Language) with joins, aggregations, named parameters, and transaction management with ACID compliance.

---

## Phase 1: The Concept (Brief)

**Time:** 5 minutes

- "Yesterday we looked at Criteria API and Native SQL. Today we look at the most common querying method in Hibernate: HQL."
- "HQL looks like SQL, but there is one massive difference: HQL queries **Java Classes and Properties**, not Database Tables and Columns."
- Reference the written content on Advanced Hibernate (ACID, Object States).

---

## Phase 2: The Code (Live Implementation)

**Time:** 25-35 minutes

### Step 1: The Model setup

- Quickly review the mapped entities: `Department`, `Employee`, and `Project`.
- Ensure there is a `@ManyToOne` from Employee to Department, and a `@ManyToMany` between Employee and Project.

### Step 2: Basic HQL and Named Parameters

Open `code/HqlDemoService.java` and write `findEmployeesByDepartment()`:

```java
public List<Employee> findEmployeesByDept(String deptName) {
    String hql = "SELECT e FROM Employee e WHERE e.department.name = :deptName";
    return session.createQuery(hql, Employee.class)
                  .setParameter("deptName", deptName)
                  .getResultList();
}
```

- "Notice I said `e.department.name`. I am navigating the object graph using dot notation. Hibernate translates this into an SQL `INNER JOIN` automatically!"

### Step 3: Explicit Joins and Fetch Joins

Write `findDepartmentWithEmployees()`:

```java
public Department getDepartmentWithEmployees(Long deptId) {
    String hql = "SELECT d FROM Department d JOIN FETCH d.employees WHERE d.id = :id";
    return session.createQuery(hql, Department.class)
                  .setParameter("id", deptId)
                  .uniqueResult();
}
```

- Explain the `JOIN FETCH` keyword. "This is crucial for performance. It tells Hibernate to eagerly load the employees collection in the exact same SQL statement, preventing the N+1 query problem."

### Step 4: Aggregations

Write `getAverageSalaryByDepartment()`:

```java
public List<Object[]> getAverageSalaryByDepartment() {
    String hql = "SELECT e.department.name, AVG(e.salary) FROM Employee e GROUP BY e.department.name";
    return session.createQuery(hql, Object[].class).getResultList();
}
```

- "Just like SQL, we can use `SUM`, `AVG`, `COUNT`, and `GROUP BY`. But notice the return type is a `List<Object[]>`, not entities, because we are returning a projection (scalar values)."

### Step 5: Transaction Management (ACID)

Open `code/TransactionDemoService.java`.

Write `promoteAndTransferEmployee()`:

```java
public void promoteAndTransfer(Long empId, Long newDeptId) {
    Transaction tx = null;
    try {
        tx = session.beginTransaction();
        
        Employee emp = session.get(Employee.class, empId);
        Department newDept = session.get(Department.class, newDeptId);
        
        // 1. Give raise
        emp.setSalary(emp.getSalary() * 1.10);
        
        // Let's simulate an error happening halfway through...
        if (newDept == null) {
            throw new RuntimeException("Target department does not exist!");
        }
        
        // 2. Transfer
        emp.setDepartment(newDept);
        
        tx.commit();
    } catch (Exception e) {
        if (tx != null) tx.rollback();
        System.out.println("Transaction rolled back due to error: " + e.getMessage());
    }
}
```

- Run the method with a valid department ID, show it succeeds.
- Run the method with an invalid department ID. Show that the exception is caught, the transaction rolls back, and the employee **does not** keep the 10% raise even though the `setSalary` line executed before the error. "This is Atomicity -- all or nothing."

---

## Key Talking Points

- **HQL operates on objects, not tables.** Keep your mind in the Java world.
- **JOIN FETCH is your best friend.** Always use it when you know you need the child collection.
- **Transactions guarantee data integrity.** If a transfer fails, the raise must roll back too.
