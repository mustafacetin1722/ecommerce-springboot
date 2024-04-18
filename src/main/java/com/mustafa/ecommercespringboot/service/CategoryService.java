package com.mustafa.ecommercespringboot.service;

import com.mustafa.ecommercespringboot.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    String createCategory(CategoryDto categoryDto);
    String updateCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryByUuid(String  categoryUuid);
    void deleteCategory(Long categoryId);
}
