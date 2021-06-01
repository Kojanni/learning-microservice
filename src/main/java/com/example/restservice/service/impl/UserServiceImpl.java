package com.example.restservice.service.impl;

import com.example.restservice.model.User;
import com.example.restservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final List<User> users = new ArrayList<>();

    {
        users.add(new User("Ivan", "Vladimirovich", "Pushka", 35));
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User getUserById(long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteUserById(long id) {
        User user = getUserById(id);
        users.remove(user);
    }

    @Override
    public void createUser(User user) {
        users.add(user);
    }

    @Override
    public void updateUser(User user) {
        users.stream()
                .filter(userOld -> userOld.getId() == user.getId())
                .findFirst()
                .map(userOld -> {
                    userOld.setFirstName(user.getFirstName());
                    userOld.setMiddleName(user.getMiddleName());
                    userOld.setLastName(user.getLastName());
                    userOld.setAge(user.getAge());

                    return userOld;
                });
    }
}
