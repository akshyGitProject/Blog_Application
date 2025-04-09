package com.example.blog.BlogAppProject.repositories;

import com.example.blog.BlogAppProject.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
