package com.mustafa.ecommercespringboot.service.impl;

import com.mustafa.ecommercespringboot.dto.CategoryDto;
import com.mustafa.ecommercespringboot.mapper.CategoryMapper;
import com.mustafa.ecommercespringboot.model.Category;
import com.mustafa.ecommercespringboot.repository.CategoryRepository;
import com.mustafa.ecommercespringboot.service.CategoryService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String createCategory(CategoryDto categoryDto) {
        Category category = this.categoryMapper.toCategory(categoryDto);
        this.categoryRepository.save(category);
        return "Category successfully created.";
    }

    @Override
    public String updateCategory(CategoryDto categoryDto) {
        Category category = this.categoryRepository.findByUuid(categoryDto.getUuid())
                .orElseThrow(() -> new RuntimeException("Category not found."));
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        this.categoryRepository.save(category);
        return "Category successfully updated.";
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = categoryList.stream()
                .map(category -> this.categoryMapper.toCategoryDto(category)).collect(Collectors.toList());
        return categoryDtoList;
    }

    @Override
    public CategoryDto getCategoryByUuid(String categoryUuid) {
        Category category = this.categoryRepository.findByUuid(categoryUuid)
                .orElseThrow(() -> new RuntimeException("Category not found."));
        CategoryDto categoryDto = this.categoryMapper.toCategoryDto(category);
        return categoryDto;
    }

    @Override
    public void deleteCategory(Long categoryId){
    Category category = this.categoryRepository.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found."));
    this.categoryRepository.delete(category);
    }
}
