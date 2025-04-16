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
//    @Autowired
//    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categories = categoryRepo.findAll();
        return categories.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        CategoryDto categoryDto = toDto(category);

        return categoryDto;
    }

    @Override
    public CategoryDto deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        categoryRepo.delete(category);

        return toDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        // Set new values from DTO
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = categoryRepo.save(category);

        return toDto(updatedCategory);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category savedCategory = categoryRepo.save(category);

        CategoryDto responseDto = new CategoryDto();
        responseDto.setCategoryId(savedCategory.getCategoryId());
        responseDto.setCategoryTitle(savedCategory.getCategoryTitle());
        responseDto.setCategoryDescription(savedCategory.getCategoryDescription());

        return responseDto;
    }

//    public Category categoryDtoToCategory(CategoryDto categoryDto){
//
//        Category category = modelMapper.map(categoryDto, Category.class);
//        return category;
//
//    }

//    public CategoryDto categoryToCategoryDto(Category category){
//
//        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
//
//        return categoryDto;
//
//    }

    // Manual mapper: Entity -> DTO
    private CategoryDto toDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryTitle(category.getCategoryTitle());
        categoryDto.setCategoryDescription(category.getCategoryDescription());
        return categoryDto;
    }

    // Manual mapper: DTO -> Entity
    private Category toEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        return category;
    }
}
