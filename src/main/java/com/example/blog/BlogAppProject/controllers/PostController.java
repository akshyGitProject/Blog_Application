package com.example.blog.BlogAppProject.controllers;

import com.example.blog.BlogAppProject.entities.Post;
import com.example.blog.BlogAppProject.payloads.ApiResponse;
import com.example.blog.BlogAppProject.payloads.PostDto;
import com.example.blog.BlogAppProject.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    //Creating Post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId){
        PostDto postDto1 = postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(postDto1, HttpStatus.CREATED);
    }

    //Getting Post By User
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userId")int userId){

        List<PostDto> posts=postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    //Getting Post by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("categoryId")int categoryId){

        List<PostDto> posts=postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    //GetAll Posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPost(){

        List<PostDto> posts=this.postService.getAllPosts();

        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    //Get Single Post by PostId
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable int postId){
        PostDto postDto=postService.getPostBYId(postId);

        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }

    //DeletePost:
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable int postId){

        postService.deletePost(postId);

        return new ApiResponse("Post is SuccessFully Deleted!!",true);

    }

    //UpdatePost
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                              @PathVariable int postId){

        PostDto updatePost=this.postService.updatePost(postDto,postId);

        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);

    }


}
