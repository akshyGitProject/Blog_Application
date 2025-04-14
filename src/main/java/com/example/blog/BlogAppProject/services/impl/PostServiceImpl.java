package com.example.blog.BlogAppProject.services.impl;

import com.example.blog.BlogAppProject.entities.Category;
import com.example.blog.BlogAppProject.entities.Post;
import com.example.blog.BlogAppProject.entities.User;
import com.example.blog.BlogAppProject.exceptions.ResourceNotFoundException;
import com.example.blog.BlogAppProject.payloads.PostDto;
import com.example.blog.BlogAppProject.repositories.CategoryRepo;
import com.example.blog.BlogAppProject.repositories.PostRepo;
import com.example.blog.BlogAppProject.repositories.UserRepo;
import com.example.blog.BlogAppProject.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {

        //Fetch User
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        //Fetch Category
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));

        //Create Post
        Post post = modelMapper.map(postDto, Post.class);
       // post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post post1=this.postRepo.save(post);

        return  this.modelMapper.map(post1,PostDto.class);
    }

    @Override
    public Post updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public PostDto getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        return List.of();
    }

    @Override
    public Post getPostBYId(Integer postId) {
        return null;
    }

    @Override
    public List<Post> getPostByCategory(Integer categoryId) {
        return List.of();
    }

    @Override
    public List<Post> getPostByUser(Integer userId) {
        return List.of();
    }

    @Override
    public List<Post> searchPostByKeyword(String keyword) {
        return List.of();
    }


}
