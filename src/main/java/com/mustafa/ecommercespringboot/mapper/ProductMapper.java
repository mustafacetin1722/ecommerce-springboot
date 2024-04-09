package com.mustafa.ecommercespringboot.mapper;

import com.mustafa.ecommercespringboot.dto.ProductDto;
import com.mustafa.ecommercespringboot.model.Product;

import java.util.List;

public interface ProductMapper {
    ProductDto toProductDto(Product product);
    Product toProduct(ProductDto productDto);
    List<ProductDto> toProductDtoList(List<Product> productList);
    List<Product> toProductList(List<ProductDto> productDtoList);
}
