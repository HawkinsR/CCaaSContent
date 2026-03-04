package com.revature.nosql.exercise.controller;

import com.revature.nosql.exercise.model.Post;
import com.revature.nosql.exercise.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostRepository repository;

    @Autowired
    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Post createPost(@RequestBody Post newPost) {
        // Cassandra usually expects the application to generate the UUID
        newPost.setId(UUID.randomUUID());
        return repository.save(newPost);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return repository.findAll();
    }
}
