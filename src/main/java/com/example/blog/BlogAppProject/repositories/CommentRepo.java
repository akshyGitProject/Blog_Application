package com.example.blog.BlogAppProject.repositories;

import com.example.blog.BlogAppProject.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
