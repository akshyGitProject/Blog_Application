package com.example.blog.BlogAppProject.payloads;

import com.example.blog.BlogAppProject.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private String title;
    private String content;

    //private String imageName="default.png";

    private Date addedDate;

    private UserDto userDto;

    private CategoryDto categoryDto;


}
