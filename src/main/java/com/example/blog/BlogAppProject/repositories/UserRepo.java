package com.example.blog.BlogAppProject.repositories;

import com.example.blog.BlogAppProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepo extends JpaRepository<User,Integer>{
    User findByEmail(String email);

}
