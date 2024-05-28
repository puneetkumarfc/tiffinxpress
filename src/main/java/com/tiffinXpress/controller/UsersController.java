package com.tiffinXpress.controller;

import com.tiffinXpress.model.Users;
import com.tiffinXpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UserService usersService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody Users users){
        usersService.registerUser(users);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
}
