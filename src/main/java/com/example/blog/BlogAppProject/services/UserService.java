package com.example.blog.BlogAppProject.services;

import com.example.blog.BlogAppProject.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser( UserDto user,Integer userId);
    void deleteUser(Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();

}
