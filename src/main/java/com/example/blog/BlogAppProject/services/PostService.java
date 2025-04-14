package com.example.blog.BlogAppProject.services;

import com.example.blog.BlogAppProject.entities.Post;
import com.example.blog.BlogAppProject.payloads.PostDto;

import java.util.List;

public interface PostService {


    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    Post updatePost(PostDto postDto,Integer postId);
    void deletePost(Integer postId);
    PostDto getPostById(Integer postId);
    List<Post> getAllPosts();
    Post getPostBYId(Integer postId);

    List<Post> getPostByCategory(Integer categoryId);

    List<Post> getPostByUser(Integer userId);

    List<Post> searchPostByKeyword(String keyword);


    

}
