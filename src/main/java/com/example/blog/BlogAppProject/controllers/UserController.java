package com.example.blog.BlogAppProject.controllers;

import com.example.blog.BlogAppProject.payloads.ApiResponse;
import com.example.blog.BlogAppProject.payloads.UserDto;
import com.example.blog.BlogAppProject.services.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> creteUser( @Valid @RequestBody UserDto userDto){

        UserDto createUserDto = userService.createUser(userDto);

        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId")int userId){

        UserDto userDto1=userService.updateUser(userDto,userId);
        return new ResponseEntity<>(userDto1,HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deletUser(@PathVariable("userId")int userId){

        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",false),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId")int userId){
        UserDto userDto=userService.getUserById(userId);

        return new ResponseEntity<>(userDto,HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        List<UserDto> list = userService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK); // or just ResponseEntity.ok(list)
    }
}
