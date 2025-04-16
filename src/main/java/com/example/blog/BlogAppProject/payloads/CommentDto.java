package com.example.blog.BlogAppProject.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private int commentId;

    private String content;

    private PostDto postDto;

    //private UserDto userDto;
}
