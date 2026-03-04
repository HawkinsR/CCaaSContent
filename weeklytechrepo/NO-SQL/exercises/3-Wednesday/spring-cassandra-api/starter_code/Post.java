package com.revature.nosql.exercise.model;

import java.util.UUID;

// TODO: Add the Spring Data Cassandra annotation to map this class to the "posts" table
public class Post {

    // TODO: Add the Spring Data Cassandra annotation to make this the Partition Key
    private UUID id;

    private String author;
    private String content;

    public Post() {
    }

    public Post(UUID id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    // Getters and Setters omitted for brevity but required for Jackson/Spring
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
