package com.example.blog.BlogAppProject.repositories;

import com.example.blog.BlogAppProject.entities.Comment;
import com.example.blog.BlogAppProject.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

    List<Comment> findByPost(Post post);
}
