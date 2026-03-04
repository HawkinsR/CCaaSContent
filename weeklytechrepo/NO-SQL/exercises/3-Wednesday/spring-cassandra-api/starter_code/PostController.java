package com.revature.nosql.exercise.controller;

import com.revature.nosql.exercise.model.Post;
import com.revature.nosql.exercise.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {

    // TODO: Autowire the Repository here

    // TODO: Create a @PostMapping that accepts a @RequestBody Post.
    // Ensure you generate a new UUID for the post before saving it to Cassandra!

    // TODO: Create a @GetMapping that returns the List of all Posts from Cassandra.

}
