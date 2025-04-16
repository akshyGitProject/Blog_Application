package com.example.blog.BlogAppProject.controllers;

import com.example.blog.BlogAppProject.payloads.ApiResponse;
import com.example.blog.BlogAppProject.payloads.CommentDto;
import com.example.blog.BlogAppProject.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // CREATE Comment
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable Integer postId
            ) {

        CommentDto createdComment = commentService.createComment(commentDto, postId);
        return new ResponseEntity<CommentDto>(createdComment, HttpStatus.CREATED);
    }

    // DELETE Comment
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>
                (new ApiResponse("Comment deleted successfully",true), HttpStatus.OK);
    }

    // GET Comment by ID
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Integer commentId) {
        CommentDto commentDto = commentService.getCommentById(commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    // GET Comments by Post ID
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPost(@PathVariable Integer postId) {
        List<CommentDto> comments = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
