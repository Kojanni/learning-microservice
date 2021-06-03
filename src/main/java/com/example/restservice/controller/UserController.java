package com.example.restservice.controller;

import com.example.restservice.model.User;
import com.example.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<Collection<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<User> postUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user),HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<User> putUser(@RequestBody User user) {
        return new ResponseEntity<>( userService.updateUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
