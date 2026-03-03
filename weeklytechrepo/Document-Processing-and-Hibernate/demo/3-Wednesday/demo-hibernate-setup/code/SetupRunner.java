package com.example.demoapp;

import com.example.demoapp.model.Department;
import com.example.demoapp.model.Employee;
import com.example.demoapp.repository.DepartmentRepository;
import com.example.demoapp.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Runs automatically when the Spring application starts.
 * Used to insert test data and verify that Hibernate mapping is working.
 */
@Component
public class SetupRunner implements CommandLineRunner {

    private final EmployeeRepository employeeRepo;
    private final DepartmentRepository departmentRepo;

    public SetupRunner(EmployeeRepository employeeRepo, DepartmentRepository departmentRepo) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- Starting DB Initialization ---");

        // Create and save a Department
        Department engineering = new Department("Engineering");
        departmentRepo.save(engineering);

        // Create and save Employees belonging to the Department
        employeeRepo.save(new Employee("Alice Johnson", 80000, engineering));
        employeeRepo.save(new Employee("Bob Smith", 75000, engineering));

        System.out.println("--- Data Initialization Complete ---");

        // Fetch and print to verify
        employeeRepo.findAll().forEach(emp -> {
            System.out.println("Found: " + emp.getName() + " in " + emp.getDepartment().getName());
        });
    }
}
