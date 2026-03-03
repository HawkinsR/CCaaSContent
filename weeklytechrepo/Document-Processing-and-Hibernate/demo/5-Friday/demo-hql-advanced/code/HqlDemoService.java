package com.example.demoapp.service;

import com.example.demoapp.model.Department;
import com.example.demoapp.model.Employee;
import org.hibernate.Session;

import java.util.List;

/**
 * Service demonstrating HQL (Hibernate Query Language) capabilities.
 */
public class HqlDemoService {

    private final Session session;

    public HqlDemoService(Session session) {
        this.session = session;
    }

    /**
     * Demonstrates dot-notation implicit joins.
     */
    public List<Employee> findEmployeesByDept(String deptName) {
        String hql = "SELECT e FROM Employee e WHERE e.department.name = :deptName";
        return session.createQuery(hql, Employee.class)
                .setParameter("deptName", deptName)
                .getResultList();
    }

    /**
     * Demonstrates an explicit JOIN FETCH to prevent the N+1 query problem.
     * Eagerly loads the lazy 'employees' collection.
     */
    public Department getDepartmentWithEmployees(Long deptId) {
        String hql = "SELECT d FROM Department d JOIN FETCH d.employees WHERE d.id = :id";
        return session.createQuery(hql, Department.class)
                .setParameter("id", deptId)
                .uniqueResult();
    }

    /**
     * Demonstrates HQL aggregation and scalar projections.
     */
    public List<Object[]> getAverageSalaryByDepartment() {
        String hql = "SELECT e.department.name, AVG(e.salary) " +
                "FROM Employee e GROUP BY e.department.name";
        return session.createQuery(hql, Object[].class).getResultList();
    }
}
