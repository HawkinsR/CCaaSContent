package com.example.demoapp.service;

import com.example.demoapp.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    /**
     * Returns a hardcoded list of employees for demo purposes.
     * In a real application, this would query a database via a Repository.
     */
    public List<Employee> getAllEmployees() {
        return Arrays.asList(
            new Employee(1L, "Alice", "Engineering"),
            new Employee(2L, "Bob", "Marketing"),
            new Employee(3L, "Charlie", "Engineering")
        );
    }
}
