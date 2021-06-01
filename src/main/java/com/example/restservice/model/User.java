package com.example.restservice.model;

import lombok.Data;

@Data
public class User {
    private static long counter;

    private long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private int age;
}
