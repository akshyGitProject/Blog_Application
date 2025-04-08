package com.example.blog.BlogAppProject.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {


    private int id;
    @NotEmpty
    @Size(min = 3,max=15,message = "Name must be at least 3 characters long")
    private String name;
    @Email(message = "Email is not valid")
    private String email;
    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password must not be empty")
    @Size(min = 4, max = 10, message = "Password must be between 4 and 10 characters long")
    private String password;
    @NotEmpty(message = "About must not be empty")
    @Size(min = 10,max =500,message = "About must be between 10 and 500 characters long")
    private String about;

}
