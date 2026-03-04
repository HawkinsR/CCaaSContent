package com.revature.nosql.demo;

import com.revature.nosql.demo.model.Product;
import com.revature.nosql.demo.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class NoSqlDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoSqlDemoApplication.class, args);
    }

    // This Bean executes immediately after the Spring Application Context is fully
    // loaded.
    @Bean
    public CommandLineRunner populateData(ProductRepository repository) {
        return args -> {
            System.out.println("--- Starting Cassandra Data Initialization ---");

            // 1. Create Java Objects
            Product p1 = new Product(UUID.randomUUID(), "Mechanical Keyboard", 149.99);
            Product p2 = new Product(UUID.randomUUID(), "Wireless Mouse", 89.50);

            // 2. Perform the DML Insert.
            // Spring Data translates this into: INSERT INTO products (id, name, price) ...
            repository.save(p1);
            repository.save(p2);
            System.out.println("Finished saving mock products to the Cassandra Docker Node.");

            // 3. Perform a DQL Select.
            System.out.println("--- Fetching all products ---");
            repository.findAll().forEach(
                    product -> System.out.println("Found: " + product.getName() + " - $" + product.getPrice()));
        };
    }
}
