package com.mustafa.ecommercespringboot.mapper;

import com.mustafa.ecommercespringboot.dto.CategoryDto;
import com.mustafa.ecommercespringboot.model.Category;

public interface CategoryMapper {
    CategoryDto toCategoryDto(Category category);
    Category toCategory(CategoryDto categoryDto);
}
