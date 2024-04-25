package com.mustafa.ecommercespringboot.mapper.impl;

import com.mustafa.ecommercespringboot.dto.ProductDto;
import com.mustafa.ecommercespringboot.dto.PromotionDto;
import com.mustafa.ecommercespringboot.mapper.ProductMapper;
import com.mustafa.ecommercespringboot.model.Category;
import com.mustafa.ecommercespringboot.model.Product;
import com.mustafa.ecommercespringboot.model.Promotion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapperImpl implements ProductMapper {
    @Override
    public ProductDto toProductDto(Product product) {

        List<PromotionDto> promotionDtoList = new ArrayList<>();
        for (Promotion promotion : product.getPromotionList()) {
            PromotionDto promotionDto = new PromotionDto();
            promotionDtoList.add(promotionDto);
        }
        return new ProductDto(
                product.getName(),
                product.getUuid(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getCategory().getUuid(),
                promotionDtoList
        );
    }

    @Override
    public Product toProduct(ProductDto productDto) {

        Product product = new Product();

        product.setName(productDto.getName());
        product.setUuid(productDto.getUuid());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCreatedAt(productDto.getCreatedAt());
        product.setUpdatedAt(productDto.getUpdatedAt());

        Category category = new Category();
        category.setUuid(productDto.getCategoryUuid());
        product.setCategory(category);

        List<Promotion> promotionDtoList = new ArrayList<>();
        for (PromotionDto promotionDto : productDto.getPromotionList()) {
            Promotion promotion = new Promotion();
            promotion.setUuid(promotionDto.getUuid());
            promotion.setName(promotionDto.getName());
            promotion.setDescription(promotionDto.getDescription());
            promotion.setDiscount(promotionDto.getDiscount());
            promotion.setCode(promotionDto.getCode());
            promotionDtoList.add(promotion);
        }
        product.setPromotionList(promotionDtoList);

        return product;
    }

    @Override
    public List<ProductDto> toProductDtoList(List<Product> productList) {
        return productList.stream()
                .map(product -> toProductDto(product)).collect(Collectors.toList());
    }

    @Override
    public List<Product> toProductList(List<ProductDto> productDtoList) {
        return productDtoList.stream()
                .map(productDto -> toProduct(productDto)).collect(Collectors.toList());
    }
}
