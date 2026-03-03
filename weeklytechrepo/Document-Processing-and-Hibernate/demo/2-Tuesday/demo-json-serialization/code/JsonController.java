package com.example.demoapp.controller;

import com.example.demoapp.model.Employee;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller demonstrating JSON serialization.
 *
 * - GET /api/json/jackson -- Spring auto-serializes via Jackson (default).
 * - GET /api/json/gson -- Manual serialization via Gson for comparison.
 * - POST /api/json/parse -- Spring auto-deserializes incoming JSON
 * via @RequestBody.
 */
@RestController
@RequestMapping("/api/json")
public class JsonController {

    private final Gson gson = new Gson();

    /**
     * Returns an Employee serialized to JSON by Spring's built-in Jackson.
     * No explicit ObjectMapper call needed.
     */
    @GetMapping("/jackson")
    public Employee getWithJackson() {
        return new Employee(1L, "Alice Johnson", "Engineering");
    }

    /**
     * Returns an Employee serialized to JSON manually using Gson.
     */
    @GetMapping(value = "/gson", produces = "application/json")
    public String getWithGson() {
        Employee employee = new Employee(2L, "Bob Smith", "Marketing");
        return gson.toJson(employee);
    }

    /**
     * Accepts a JSON body, deserializes it via Jackson (@RequestBody),
     * and returns it back as JSON.
     */
    @PostMapping("/parse")
    public Employee parseJson(@RequestBody Employee employee) {
        System.out.println("Received: " + employee.getName());
        return employee;
    }
}
