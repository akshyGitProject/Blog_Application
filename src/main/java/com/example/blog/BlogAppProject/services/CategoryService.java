package com.example.blog.BlogAppProject.services;

import com.example.blog.BlogAppProject.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    //Create Category
    CategoryDto createCategory(CategoryDto categoryDto);

    //Update Category
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    //Delete Category
   CategoryDto deleteCategory(Integer categoryId);

    //GetSingle User
    CategoryDto getCategoryById(Integer categoryId);

    //getAllUser
    List<CategoryDto> getAllCategories();


}
