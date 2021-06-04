package com.example.restservice.model;

import com.example.restservice.model.enums.Role;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "middle_name")
    private String middleName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(unique = true)
    private String email;

    @NonNull
    private String password;

    @NonNull
    @Column(name = "is_admin")
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
