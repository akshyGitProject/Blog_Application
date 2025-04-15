package com.example.blog.BlogAppProject.services;

import com.example.blog.BlogAppProject.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId);

    void deleteComment(Integer commentId);
}
