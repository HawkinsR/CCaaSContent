package com.example.demoapp.demo;

import com.example.demoapp.model.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Standalone demo class showing Gson usage.
 * Compare the API simplicity with Jackson's ObjectMapper.
 */
public class GsonDemo {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // --- Serialization: Object to JSON ---
        Employee employee = new Employee(1L, "Alice Johnson", "Engineering");
        String json = gson.toJson(employee);
        System.out.println("Gson Serialized JSON:");
        System.out.println(json);

        // --- Deserialization: JSON to Object ---
        String inputJson = "{\"id\":2,\"name\":\"Bob Smith\",\"department\":\"Marketing\"}";
        Employee parsed = gson.fromJson(inputJson, Employee.class);
        System.out.println("\nGson Deserialized Object:");
        System.out.println("Name: " + parsed.getName());
        System.out.println("Department: " + parsed.getDepartment());
    }
}
