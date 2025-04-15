package com.example.blog.BlogAppProject.payloads;

import com.example.blog.BlogAppProject.entities.Category;
import com.example.blog.BlogAppProject.entities.Comment;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    private Set<Comment> comments=new HashSet<>();


}
