package com.example.blog.BlogAppProject.repositories;

import com.example.blog.BlogAppProject.entities.Category;
import com.example.blog.BlogAppProject.entities.Post;
import com.example.blog.BlogAppProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

   // List<User> findAllUsers();
    //List<Category> findAllCategories();
}
