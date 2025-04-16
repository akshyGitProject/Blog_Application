package com.example.blog.BlogAppProject.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Integer categoryId;

    @NotEmpty
    @Size(min = 3,max=20,message = "Name must be at least 3 characters long")
    private String categoryTitle;

    @NotEmpty
    @Size(min = 10,max=1000,message = "About must be between 10 and 1000 characters long")
    private String categoryDescription;

}
