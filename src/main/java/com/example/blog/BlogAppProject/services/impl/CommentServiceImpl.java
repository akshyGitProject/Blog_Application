package com.example.blog.BlogAppProject.services.impl;

import com.example.blog.BlogAppProject.payloads.CommentDto;
import com.example.blog.BlogAppProject.repositories.CommentRepo;
import com.example.blog.BlogAppProject.repositories.PostRepo;
import com.example.blog.BlogAppProject.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        return null;
    }

    @Override
    public void deleteComment(Integer commentId) {

    }
}
