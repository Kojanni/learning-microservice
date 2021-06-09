package com.example.restservice.model;

import com.example.restservice.model.enums.Role;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Data
public class User {

    private int id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String password;

    private boolean isAdmin;

    private int age;

    public User(String firstName, String middleName, String lastName, int age) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
    }

    public User() {
    }

    public Role getRole() {
        if (isAdmin) {
            return Role.ADMIN;
        }

        return Role.USER;
    }
}
