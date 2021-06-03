package com.example.restservice.service.impl;

import com.example.restservice.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserServiceImplTest {

    @Autowired
    public UserServiceImpl userService;

    @Test
    public void create() {
        User user = new User("fName", "mName", "lName", 27);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser.getId());
        assertEquals(createdUser.getFirstName(), user.getFirstName());
        assertEquals(createdUser.getMiddleName(), user.getMiddleName());
        assertEquals(createdUser.getLastName(), user.getLastName());
        assertEquals(createdUser.getAge(), user.getAge());
    }

    @Test
    public void updateExistedUser() {
        User user = new User("fName", "mName", "lName2", 27);
        user.setId(1);

        User createdUser = userService.updateUser(user);

        assertEquals(createdUser.getFirstName(), user.getFirstName());
        assertEquals(createdUser.getMiddleName(), user.getMiddleName());
        assertEquals(createdUser.getLastName(), user.getLastName());
        assertEquals(createdUser.getAge(), user.getAge());

        user.setLastName("newLName");

        createdUser = userService.updateUser(user);
        assertEquals(createdUser.getFirstName(), user.getFirstName());
        assertEquals(createdUser.getMiddleName(), user.getMiddleName());
        assertEquals(createdUser.getLastName(), user.getLastName());
        assertEquals(createdUser.getAge(), user.getAge());
    }

    @Test
    public void updateIfNotExistedUser() {
        User user = new User("fName", "mName", "lName2", 27);
        user.setId(100);

        assertThrows(UserServiceImpl.UserNotFoundException.class, () -> userService.updateUser(user));
    }

    @Test
    public void getUsers() {
        assertTrue(userService.getUsers().size() == 3);
    }

    @Test
    public void getUserById() {
        User user = userService.getUserById(1);

        assertEquals(user.getFirstName(), "Ivan");
        assertEquals(user.getMiddleName(), "Vladimirovich");
        assertEquals(user.getLastName(),"Pushka");
        assertEquals(user.getAge(), 35);
    }

    @TestConfiguration
    static class UserServiceTestConfig {
        @Bean
        public UserServiceImpl userServiceTest() {
            return new UserServiceImpl();
        }
    }
}