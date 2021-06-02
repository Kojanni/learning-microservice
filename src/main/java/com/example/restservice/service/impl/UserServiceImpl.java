package com.example.restservice.service.impl;

import com.example.restservice.model.User;
import com.example.restservice.repository.UserRepository;
import com.example.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    @Transactional(rollbackFor = UserNotFoundException.class)
    public void deleteUserById(int id) {
       getUserById(id);
       userRepository.deleteById(id);
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = UserNotFoundException.class)
    public void updateUser(User user) {
        getUserById(user.getId());
        userRepository.save(user);
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found in DB")
    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(int id) {
        }
    }
}
