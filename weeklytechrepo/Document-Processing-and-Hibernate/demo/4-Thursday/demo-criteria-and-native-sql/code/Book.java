package com.example.demoapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
@NamedQuery(name = "Book.findByTitleKeyword", query = "SELECT b FROM Book b WHERE b.title LIKE :keyword")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private double price;

    public Book() {
    }

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getters and Setters omitted for brevity in demo layout
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title='" + title + '\'' +
                ", author='" + author + '\'' + ", price=" + price + '}';
    }
}
