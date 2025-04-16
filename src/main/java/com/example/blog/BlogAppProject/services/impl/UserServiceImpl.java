package com.example.blog.BlogAppProject.services.impl;

import com.example.blog.BlogAppProject.entities.User;
import com.example.blog.BlogAppProject.exceptions.ResourceNotFoundException;
import com.example.blog.BlogAppProject.payloads.UserDto;
import com.example.blog.BlogAppProject.repositories.UserRepo;
import com.example.blog.BlogAppProject.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired
//    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto); // Convert DTO to entity
        User savedUser = this.userRepo.save(user); // Save user to the database
        return this.userToDto(savedUser); // Convert saved entity back to DTO
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = userRepo.save(user);

        return userToDto(updatedUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        userRepo.delete(user);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            UserDto userDto = userToDto(user);
            userDtos.add(userDto);
        }

        return userDtos;
    }


    public UserDto userToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        userDto.setAbout(user.getAbout());
        return userDto;
    }

    private User dtoToUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        return user;
    }
}

