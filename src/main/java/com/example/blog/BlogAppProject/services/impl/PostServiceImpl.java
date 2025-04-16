package com.example.blog.BlogAppProject.services.impl;

import com.example.blog.BlogAppProject.entities.Category;
import com.example.blog.BlogAppProject.entities.Post;
import com.example.blog.BlogAppProject.entities.User;
import com.example.blog.BlogAppProject.exceptions.ResourceNotFoundException;
import com.example.blog.BlogAppProject.payloads.PostDto;
import com.example.blog.BlogAppProject.payloads.PostResponse;
import com.example.blog.BlogAppProject.repositories.CategoryRepo;
import com.example.blog.BlogAppProject.repositories.PostRepo;
import com.example.blog.BlogAppProject.repositories.UserRepo;
import com.example.blog.BlogAppProject.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {



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

//        //Create Post
//        Post post = modelMapper.map(postDto, Post.class);
//       // post.setImageName("default.png");
//        post.setAddedDate(new Date());
//        post.setUser(user);
//        post.setCategory(category);
//
//        Post post1=this.postRepo.save(post);
//
//        return  this.modelMapper.map(post1,PostDto.class);

        Post post = toEntity(postDto);
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savedPost = postRepo.save(post);
        return toDto(savedPost);

    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId).
                orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
       // post.setImageName(postDto.getImageName());

        Post updatedPost = postRepo.save(post);
        return toDto(updatedPost);
    }

    @Override
    public void deletePost(Integer postId) {

       Post post= postRepo.findById(postId).
               orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
       this.postRepo.delete(post);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = this.postRepo.findAll();

        List<PostDto> postDtos = posts.stream().map(post -> {
            PostDto dto = new PostDto();
            //dto.setId(post.getId());
            dto.setTitle(post.getTitle());
            dto.setContent(post.getContent());
            //dto.setImageName(post.getImageName());
            dto.setAddedDate(post.getAddedDate());
            //dto.setCategory(post.getCategory());
            //dto.setUser(post.getUser());
            return dto;
        }).collect(Collectors.toList());

        return postDtos;
    }
@Override
public PostResponse getAllPosts(int pageNumber,int pageSize, String sortBy,String sortDir) {
    Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
    Page<Post> pagePosts = postRepo.findAll(pageable);
    List<PostDto> postDtos = pagePosts.getContent().stream().map(this::toDto).collect(Collectors.toList());

    PostResponse postResponse = new PostResponse();
    postResponse.setContent(postDtos);
    postResponse.setPageNumber(pagePosts.getNumber());
    postResponse.setPageSize(pagePosts.getSize());
    postResponse.setTotalElements(pagePosts.getTotalElements());
    postResponse.setTotalPages(pagePosts.getTotalPages());
    postResponse.setLastpage(pagePosts.isLast());

    return postResponse;
}

    @Override
    public PostDto getPostBYId(Integer postId) {
//        Post post=this.postRepo.findById(postId).
//                orElseThrow(()->new ResourceNotFoundException("Post","post Id",postId));
//        return this.modelMapper.map(post,PostDto.class);

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        return toDto(post);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        List<Post> posts = postRepo.findByCategory(cat);
        return posts.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        //Finding User
        User user=userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","userId",userId));

        //Finding List of Post
        List<Post> posts=this.postRepo.findByUser(user);

        //Converting into DTO
        return posts.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = postRepo.findByTitleContaining(keyword);

        //Converting Post to PostDto
        return posts.stream().map(this::toDto).collect(Collectors.toList());
    }

    PostDto toDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setPostId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        //postDto.setImageName(post.getImageName());
        postDto.setAddedDate(post.getAddedDate());
       // postDto.setUserId(post.getUser().getId());
        //postDto.setCategoryId(post.getCategory().getCategoryId());
        return postDto;
    }

    private Post toEntity(PostDto dto) {
        Post post = new Post();
        post.setPostId(dto.getPostId());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
       // post.setImageName(dto.getImageName());
        post.setAddedDate(dto.getAddedDate());
        return post;
    }
}



