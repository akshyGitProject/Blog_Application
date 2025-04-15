package com.example.blog.BlogAppProject.services;

import com.example.blog.BlogAppProject.entities.Post;
import com.example.blog.BlogAppProject.payloads.PostDto;
import com.example.blog.BlogAppProject.payloads.PostResponse;

import java.util.List;

public interface PostService {


    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto postDto,Integer postId);
    void deletePost(Integer postId);
    PostDto getPostById(Integer postId);
    public PostResponse getAllPosts(int pageNumber, int pageSize,String sortBy,String sortDir);
    PostDto getPostBYId(Integer postId);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);


    

}
