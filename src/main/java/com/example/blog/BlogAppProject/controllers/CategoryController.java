package com.example.blog.BlogAppProject.controllers;

import com.example.blog.BlogAppProject.entities.Category;
import com.example.blog.BlogAppProject.payloads.ApiResponse;
import com.example.blog.BlogAppProject.payloads.CategoryDto;
import com.example.blog.BlogAppProject.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    //Create

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){

        CategoryDto categoryDto1 = categoryService.createCategory(categoryDto);

        return new ResponseEntity<CategoryDto>(categoryDto1, HttpStatus.CREATED);

    }
    //Update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId")int categoryId){
        CategoryDto categoryDto1 = categoryService.updateCategory(categoryDto,categoryId);

        return new ResponseEntity<CategoryDto>(categoryDto1,HttpStatus.OK);
    }

    //get
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("categoryId")int categoryId){

        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
    }

    //getAllCategories

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {

        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }
    //DeleteCaregory

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") int categoryId) {

        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category Deleted Successfully", false), HttpStatus.OK);
    }
}
