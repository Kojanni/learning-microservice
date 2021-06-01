package com.example.restservice.model;

import lombok.Data;

@Data
public class User {
    private static long counter = 0;

    private long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private int age;

    public User(String firstName, String middleName, String lastName, int age) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.id = ++counter;
    }
}
