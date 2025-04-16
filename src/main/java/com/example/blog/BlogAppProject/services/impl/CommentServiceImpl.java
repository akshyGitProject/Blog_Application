package com.example.blog.BlogAppProject.services.impl;

import com.example.blog.BlogAppProject.entities.Comment;
import com.example.blog.BlogAppProject.entities.Post;
import com.example.blog.BlogAppProject.exceptions.ResourceNotFoundException;
import com.example.blog.BlogAppProject.payloads.CommentDto;
import com.example.blog.BlogAppProject.repositories.CommentRepo;
import com.example.blog.BlogAppProject.repositories.PostRepo;
import com.example.blog.BlogAppProject.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @Autowired
    private PostServiceImpl postServiceImpl;

    @Override
    public CommentDto getById(Integer commentId){
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        return toDto(comment);
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

        Comment comment = toEntity(commentDto);
        comment.setPost(post);

        Comment savedComment = commentRepo.save(comment);

        return toDto(savedComment);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        commentRepo.delete(comment);
    }

    @Override
    public CommentDto getCommentById(Integer commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        return toDto(comment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        List<Comment> comments = commentRepo.findByPost(post);
        return comments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private CommentDto toDto(Comment comment){
        CommentDto dto = new CommentDto();
        dto.setCommentId(comment.getCommentId());
        dto.setContent(comment.getContent());
        //dto.setPostDto(postServiceImpl.toDto(comment.getPost()));
        return dto;
    }

    private Comment toEntity(CommentDto dto){
        Comment comment = new Comment();
        comment.setCommentId(dto.getCommentId());
        comment.setContent(dto.getContent());
        // Post is set separately in createComment()
        return comment;
    }
}

