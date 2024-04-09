package com.mustafa.ecommercespringboot.mapper.impl;

import com.mustafa.ecommercespringboot.dto.CategoryDto;
import com.mustafa.ecommercespringboot.dto.ProductDto;
import com.mustafa.ecommercespringboot.mapper.CategoryMapper;
import com.mustafa.ecommercespringboot.model.Category;
import com.mustafa.ecommercespringboot.model.Product;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public CategoryDto toCategoryDto(Category category) {

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setUuid(category.getUuid());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setCreatedAt(category.getCreatedAt());
        categoryDto.setUpdatedAt(category.getUpdatedAt());

        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : category.getProductList()){
            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setUuid(productDto.getUuid());
            productDto.setDescription(productDto.getDescription());
            productDto.setPrice(product.getPrice());
            productDtoList.add(productDto);
        }
        categoryDto.setProductList(productDtoList);
    return categoryDto;
    }

    @Override
    public Category toCategory(CategoryDto categoryDto) {

        Category category = new Category();
        category.setUuid(categoryDto.getUuid());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        category.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        List<Product> productList = new ArrayList<>();
        for (ProductDto productDto : categoryDto.getProductList()) {
            Product product = new Product();
            product.setUuid(productDto.getUuid());
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            productList.add(product);
        }
        category.setProductList(productList);
        return category;
    }
}
