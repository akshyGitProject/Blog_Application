package com.example.blog.BlogAppProject.controllers;

import com.example.blog.BlogAppProject.entities.Post;
import com.example.blog.BlogAppProject.payloads.PostDto;
import com.example.blog.BlogAppProject.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId){
        PostDto postDto1 = postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(postDto1, HttpStatus.CREATED);
    }


}
