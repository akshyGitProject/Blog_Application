package com.example.blog.BlogAppProject.services;

import com.example.blog.BlogAppProject.payloads.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto getById(Integer commentId);

    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer commentId);

    CommentDto getCommentById(Integer commentId);

    List<CommentDto> getCommentsByPostId(Integer postId);
}
