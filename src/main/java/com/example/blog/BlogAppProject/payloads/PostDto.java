package com.example.blog.BlogAppProject.payloads;

import com.example.blog.BlogAppProject.entities.Category;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private int postId;

    private String title;
    private String content;

    //private String imageName="default.png";

    private Date addedDate;

    private UserDto userDto;

    private CategoryDto categoryDto;


}
