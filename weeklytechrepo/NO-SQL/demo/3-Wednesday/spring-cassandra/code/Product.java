package com.revature.nosql.demo.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

// 1. The @Table annotation tells Spring Data this is a Cassandra Entity
@Table("products")
public class Product {

    // 2. The @PrimaryKey signifies this is the Partition Key.
    // (If we needed clustering columns, we would create a separate @PrimaryKeyClass)
    @PrimaryKey
    private UUID id;
    
    private String name;
    private double price;

    // Standard Constructors, Getters, Setters
    public Product() {}

    public Product(UUID id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
