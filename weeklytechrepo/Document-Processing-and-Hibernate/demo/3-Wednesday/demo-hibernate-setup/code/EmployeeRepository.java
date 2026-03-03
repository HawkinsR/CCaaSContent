package com.example.demoapp.repository;

import com.example.demoapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Employee entity.
 * Provides basic CRUD operations automatically.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Custom query methods can be defined here, e.g.:
    // List<Employee> findByDepartmentName(String deptName);
}
