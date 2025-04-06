package com.example.blog.BlogAppProject.controllers;

import com.example.blog.BlogAppProject.payloads.UserDto;
import com.example.blog.BlogAppProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> creteUser(@RequestBody UserDto userDto){

        UserDto createUserDto = userService.createUser(userDto);

        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

}
