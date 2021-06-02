package com.example.restservice.controller;

import com.example.restservice.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(UserServiceImpl.UserNotFoundException.class)
    public ResponseEntity<Void> userNotFoundException() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
