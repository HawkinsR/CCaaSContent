package com.example.demoapp.controller;

import com.example.demoapp.model.Employee;
import com.example.demoapp.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    // Constructor Injection -- the recommended approach.
    // Spring automatically injects the EmployeeService bean here.
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * GET /api/employees
     * Returns a JSON array of all employees.
     * Spring Boot auto-serializes the List<Employee> to JSON via Jackson.
     */
    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }
}
