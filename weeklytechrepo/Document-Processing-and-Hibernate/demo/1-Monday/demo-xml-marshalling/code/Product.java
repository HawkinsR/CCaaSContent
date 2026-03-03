package com.example.demoapp.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Product POJO annotated for JAXB XML binding.
 *
 * @XmlRootElement -- defines the root XML element name.
 * @XmlElement -- maps each getter to an XML child element.
 */
@XmlRootElement(name = "product")
public class Product {

    private int id;
    private String name;
    private double price;

    // No-argument constructor required by JAXB
    public Product() {
    }

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + "}";
    }
}
