package com.example.demoapp.demo;

import com.example.demoapp.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Standalone demo class showing Jackson ObjectMapper usage.
 * Run the main method to see serialization and deserialization in action.
 */
public class JacksonDemo {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // --- Serialization: Object to JSON ---
        Employee employee = new Employee(1L, "Alice Johnson", "Engineering");
        String json = mapper.writeValueAsString(employee);
        System.out.println("Serialized JSON:");
        System.out.println(json);

        // --- Deserialization: JSON to Object ---
        String inputJson = "{\"id\":2,\"name\":\"Bob Smith\",\"department\":\"Marketing\"}";
        Employee parsed = mapper.readValue(inputJson, Employee.class);
        System.out.println("\nDeserialized Object:");
        System.out.println("Name: " + parsed.getName());
        System.out.println("Department: " + parsed.getDepartment());
    }
}
