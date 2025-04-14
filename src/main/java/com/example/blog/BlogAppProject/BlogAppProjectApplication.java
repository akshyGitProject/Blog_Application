package com.example.blog.BlogAppProject;

import com.example.blog.BlogAppProject.entities.Post;
import com.example.blog.BlogAppProject.payloads.PostDto;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(BlogAppProjectApplication.class, args);
	}

//	@Bean
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();

		// Post → PostDto
		mapper.typeMap(Post.class, PostDto.class).addMappings(m -> {
			m.map(Post::getUser, PostDto::setUserDto);
			m.map(Post::getCategory, PostDto::setCategoryDto);
		});

		// Optional: Reverse mapping if needed
		mapper.typeMap(PostDto.class, Post.class).addMappings(m -> {
			m.map(PostDto::getUserDto, Post::setUser);
			m.map(PostDto::getCategoryDto, Post::setCategory);
		});

		return mapper;
	}


}
