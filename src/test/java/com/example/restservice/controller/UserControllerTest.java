package com.example.restservice.controller;

import com.example.restservice.model.User;
import com.example.restservice.repository.UserRepository;
import com.example.restservice.service.UserService;
import com.example.restservice.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;


    @Test
    public void getUsers() throws Exception {
        User user = new User();
        List<User> users = Collections.singletonList(user);

        given(userService.getUsers()).willReturn(users);

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getUserById() throws Exception {
        Mockito mockito = new Mockito();
        int id = mockito.anyInt();

        User user = new User("fName", "mName", "lName", 27);
        user.setId(id);

        given(userService.getUserById(id)).willReturn(user);

        mockMvc.perform(get("/users/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.middleName", is(user.getMiddleName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.age", is(user.getAge())));
    }

    @Test
    public void getUserByIdNotFoundException() throws Exception {
        Mockito mockito = new Mockito();
        int id = mockito.anyInt();

        given(userService.getUserById(id)).willThrow(new UserServiceImpl.UserNotFoundException(id));

        mockMvc.perform(get("/users/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void postUser() throws Exception {
        User user = new User("fName", "mName", "lName", 27);

        when(userService.createUser(user)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void postUserIsNotCreated() throws Exception {
        User user = new User("fName", "mName", "lName", 27);

        when(userService.createUser(user)).thenThrow(new UserServiceImpl.UserNotImplementedException(user));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotImplemented());
    }

    @Test
    public void putUser() throws Exception {
        User user = new User("fName", "mName", "lName", 27);

        when(userService.updateUser(user)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void putUserIsNotUpdate() throws Exception {
        User user = new User("fName", "mName", "lName", 27);

        when(userService.updateUser(user)).thenThrow(new UserServiceImpl.UserNotImplementedException(user));

        mockMvc.perform(MockMvcRequestBuilders
                .put("/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotImplemented());
    }

    @Test
    public void deleteUser() throws Exception {
        Mockito mockito = new Mockito();
        int id = mockito.anyInt();

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/users/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}