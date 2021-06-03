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
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User createUser(User user) {
        return Optional.of(userRepository.save(user)).orElseThrow(() -> new UserNotImplementedException(user));
    }

    @Override
    public User updateUser(User user) {
        getUserById(user.getId());
        return Optional.of(userRepository.save(user)).orElseThrow(() -> new UserNotImplementedException(user));
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found in DB")
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(int id) {
        }
    }

    @ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED)
    public static class UserNotImplementedException extends RuntimeException {
        public UserNotImplementedException(User user) {
        }
    }
}
