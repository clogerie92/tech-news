package com.technews.controller;

import com.technews.model.Comment;
import com.technews.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentRepository repository;
    // GET request to /api/comments
    @GetMapping("/api/comments")
    // get all comments
    public List<Comment> getAllComments() {
        return repository.findAll();
    }
    // GET request to /api/comments/{id} --> get single comment
    @GetMapping("/api/comments/{id}")
    public Comment getComment(@PathVariable int id) {
        return repository.getById(id);
    }
    // POST request to /api/comments
    @PostMapping("/api/comments")
    @ResponseStatus(HttpStatus.CREATED)
    // post a comment
    public Comment createComment(@RequestBody Comment comment) {
        return repository.save(comment);
    }
    // PUT request to /api/updateComment
    @PutMapping("/api/updateComment")
    // update a comment
    public Comment updateComment(@RequestBody Comment comment) {
        return repository.save(comment);
    }
    // DELETE request to /api/comments/{id} --> delete single comment
    @DeleteMapping("/api/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable int id) {
        repository.deleteById(id);
    }
}