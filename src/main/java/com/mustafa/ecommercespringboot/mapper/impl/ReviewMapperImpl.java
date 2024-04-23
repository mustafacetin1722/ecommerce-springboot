package com.mustafa.ecommercespringboot.mapper.impl;

import com.mustafa.ecommercespringboot.dto.ReviewDto;
import com.mustafa.ecommercespringboot.mapper.ReviewMapper;
import com.mustafa.ecommercespringboot.model.Product;
import com.mustafa.ecommercespringboot.model.Review;
import com.mustafa.ecommercespringboot.model.User;
import com.mustafa.ecommercespringboot.repository.ProductRepository;
import com.mustafa.ecommercespringboot.repository.UserRepository;

public class ReviewMapperImpl implements ReviewMapper {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ReviewMapperImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Review toReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setUuid(reviewDto.getUuid());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setCreatedAt(reviewDto.getCreatedAt());
        Product product = this.productRepository.findByUuid(reviewDto.getProductUuid())
                .orElseThrow(() -> new RuntimeException("Product not found."));
        review.setProduct(product);
        User user = this.userRepository.findByUuid(reviewDto.getUserUuid())
                .orElseThrow(() -> new RuntimeException("User not found."));
        review.setUser(user);
        review.setIsApproved(false);
        return review;
    }

    @Override
    public ReviewDto toReviewDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setUuid(review.getUuid());
        reviewDto.setRating(review.getRating());
        reviewDto.setComment(review.getComment());
        reviewDto.setCreatedAt(review.getCreatedAt());
        reviewDto.setProductUuid(review.getProduct().getUuid());
        reviewDto.setUserUuid(review.getUser().getUuid());
        reviewDto.setIsApproved(reviewDto.getIsApproved());
        return reviewDto;
    }
}
