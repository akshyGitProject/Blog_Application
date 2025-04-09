package com.example.blog.BlogAppProject.services.impl;


import com.example.blog.BlogAppProject.entities.Category;
import com.example.blog.BlogAppProject.exceptions.ResourceNotFoundException;
import com.example.blog.BlogAppProject.payloads.CategoryDto;
import com.example.blog.BlogAppProject.repositories.CategoryRepo;
import com.example.blog.BlogAppProject.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categories = categoryRepo.findAll();
        return categories.stream()
                .map(this::categoryToCategoryDto)
                .toList();
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        CategoryDto categoryDto = categoryToCategoryDto(category);

        return categoryDto;
    }

    @Override
    public CategoryDto deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        // Assuming there's a related user update operation
        // Implement logic to associate deletion of the category and update user here.
        // Example (requires context and userRepo):
        // userRepo.updateUserCategoryToDefault(categoryId);

        categoryRepo.delete(category);

        return categoryToCategoryDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        // Set new values from DTO
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = categoryRepo.save(category);

        return categoryToCategoryDto(updatedCategory);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryDtoToCategory(categoryDto);
        categoryRepo.save(category);
        return categoryToCategoryDto(category);
    }

    public Category categoryDtoToCategory(CategoryDto categoryDto){

        Category category = modelMapper.map(categoryDto, Category.class);
        return category;

    }

    public CategoryDto categoryToCategoryDto(Category category){

        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);

        return categoryDto;

    }


}
