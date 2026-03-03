package com.example.demoapp.service;

import com.example.demoapp.model.Department;
import com.example.demoapp.model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Service demonstrating ACID transaction management in raw Hibernate.
 */
public class TransactionDemoService {

    private final Session session;

    public TransactionDemoService(Session session) {
        this.session = session;
    }

    /**
     * Demonstrates Atomicity. Both the raise and the transfer must succeed,
     * or neither should be applied.
     */
    public void promoteAndTransfer(Long empId, Long newDeptId) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Employee emp = session.get(Employee.class, empId);
            Department newDept = session.get(Department.class, newDeptId);

            // 1. Give raise
            emp.setSalary(emp.getSalary() * 1.10);

            // Simulate an error happening halfway through if department is invalid
            if (newDept == null) {
                throw new RuntimeException("Target department does not exist!");
            }

            // 2. Transfer
            emp.setDepartment(newDept);

            // Both successfully execute
            tx.commit();
            System.out.println("Promotion and transfer successful.");
        } catch (Exception e) {
            // Rollback prevents the raise from being saved if the transfer failed
            if (tx != null)
                tx.rollback();
            System.out.println("Transaction rolled back due to error: " + e.getMessage());
        }
    }
}
