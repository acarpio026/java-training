package com.bootcamp.controller;

import com.bootcamp.model.User;
import com.bootcamp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserApi {

    private UserService service;

    @Autowired
    public UserApi(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/")
    public ResponseEntity<String> register(@RequestBody User data) {
        return service.addUser(data); 
    }

}
