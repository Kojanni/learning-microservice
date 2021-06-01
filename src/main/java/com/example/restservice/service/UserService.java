package com.example.restservice.service;

import com.example.restservice.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUserById(long id);

    void deleteUserById(long id);

    void createUser(User user);

    void updateUser(User user);
}
