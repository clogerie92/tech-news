package com.technews.controller;

import com.technews.model.Post;
import com.technews.model.User;
import com.technews.repository.UserRepository;
import com.technews.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository repository;

    @Autowired
    VoteRepository voteRepository;
    // GET request to /api/users
    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        // return a list of users
        List<User> userList = repository.findAll();
        // for each user in userList
        for (User u : userList) {
            // get all posts
            List<Post> postList = u.getPosts();
            // for each post in postList
            for (Post p : postList) {
                // set the vote count to the id of the post
                p.setVoteCount(voteRepository.countVotesByPostId(p.getId()));
            }
        }
        return userList;
    }
    // GET request to /api/users/{id} --> get single user
    @GetMapping("/api/users/{id}")
    public User getUserById(@PathVariable Integer id) {
        User returnUser = repository.getById(id);
        List<Post> postList = returnUser.getPosts();
        for (Post p : postList) {
            p.setVoteCount(voteRepository.countVotesByPostId(p.getId()));
        }

        return returnUser;
    }
    // POST request to /api/users
    @PostMapping("/api/users")
    public User addUser(@RequestBody User user) {
        // Encrypt password
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        // save new user
        repository.save(user);
        return user;
    }
    // PUT request to /api/users/{id} --> update single user
    @PutMapping("/api/users/{id}")
    // input user id into the request URL
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        User tempUser = repository.getById(id);
        // if the user is there
        if (!tempUser.equals(null)) {
            // set the id to the user
            user.setId(tempUser.getId());
            repository.save(user);
        }
        return user;
    }
    // DELETE request to /api/users/{id} --> delete single user
    @DeleteMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }

}
