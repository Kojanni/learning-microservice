package com.example.restservice.repository;

import com.example.restservice.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    Optional<User> findByEmail(String email);
}
