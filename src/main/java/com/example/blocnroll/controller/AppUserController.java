package com.example.blocnroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blocnroll.dto.AppUserDto;
import com.example.blocnroll.security.AppUser;
import com.example.blocnroll.service.AppUserService;

@RestController
@RequestMapping("/user")
public class AppUserController {

    @Autowired
    private AppUserService service;

    @PostMapping
    public ResponseEntity<AppUser> create(@RequestBody AppUserDto userDto) {
        AppUser createUser = service.createUser(userDto.getUsername(), userDto.getPassword());

        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

}
