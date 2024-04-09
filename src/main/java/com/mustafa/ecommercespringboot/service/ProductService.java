package com.mustafa.ecommercespringboot.service;

import com.mustafa.ecommercespringboot.dto.ProductDto;

import java.util.List;

public interface ProductService {
    String createProduct(ProductDto productDto);
    String updateProduct(ProductDto productDto);
    ProductDto getProductByUuid(String uuid);
    List<ProductDto> getAllProducts();
    void deleteProductById(Long id);
    List<ProductDto> getProductsByCategory(String categoryUuid);
    List<ProductDto> getProductsByUser(String userUuid);
    List<ProductDto> getProductsByPromotion(String promotionUuid);
    List<ProductDto> getProductsByPrice(double price);
    List<ProductDto> getProductsByPriceRange(double minPrice, double maxPrice);
}
