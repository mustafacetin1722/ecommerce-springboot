package com.mustafa.ecommercespringboot.service.impl;

import com.mustafa.ecommercespringboot.dto.ProductDto;
import com.mustafa.ecommercespringboot.mapper.ProductMapper;
import com.mustafa.ecommercespringboot.model.Category;
import com.mustafa.ecommercespringboot.model.Product;
import com.mustafa.ecommercespringboot.repository.CategoryRepository;
import com.mustafa.ecommercespringboot.repository.ProductRepository;
import com.mustafa.ecommercespringboot.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public String createProduct(ProductDto productDto) {
        Optional<Category> category = categoryRepository.findByUuid(productDto.getCategoryUuid());
        if (category.isEmpty()) {
            throw new RuntimeException("Category not found with this UUID: " + productDto.getCategoryUuid());
        }
        Product product = productMapper.toProduct(productDto);
        productRepository.save(product);
        return "Product created successfully";
    }

    @Override
    public String updateProduct(ProductDto productDto) {
        Optional<Category> category = categoryRepository.findByUuid(productDto.getCategoryUuid());
        if (category.isEmpty()) {
            throw new RuntimeException("Category not found with this UUID: " + productDto.getCategoryUuid());
        }
        Product product = productRepository.findByUuid(productDto.getUuid())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setUpdatedAt(productDto.getUpdatedAt());
        product.setCategory(category.get());

        productRepository.save(product);
        return "Product update successfully";
    }

    @Override
    public ProductDto getProductByUuid(String uuid) {
        Product product = productRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Product not founf"));
        return productMapper.toProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toProductDtoList(products);
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> getProductsByCategory(String categoryUuid) {
        List<Product> products = productRepository.findByCategoryUuid(categoryUuid);
        return productMapper.toProductDtoList(products);
    }

    @Override
    public List<ProductDto> getProductsByUser(String userUuid) {
        List<Product> products = productRepository.findByUserUuid(userUuid);
        return productMapper.toProductDtoList(products);
    }

    @Override
    public List<ProductDto> getProductsByPromotion(String promotionUuid) {
        List<Product> products = productRepository.findByPromotionUuid(promotionUuid);
        return productMapper.toProductDtoList(products);
    }

    @Override
    public List<ProductDto> getProductsByPrice(double price) {
        List<Product> products = productRepository.findByPrice(price);
        return productMapper.toProductDtoList(products);
    }

    @Override
    public List<ProductDto> getProductsByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = productRepository.findByPriceBetween(minPrice,maxPrice);
        return productMapper.toProductDtoList(products);
    }
}
