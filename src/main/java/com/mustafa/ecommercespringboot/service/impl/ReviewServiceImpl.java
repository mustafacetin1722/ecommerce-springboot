package com.mustafa.ecommercespringboot.service.impl;

import com.mustafa.ecommercespringboot.dto.ReviewDto;
import com.mustafa.ecommercespringboot.mapper.ReviewMapper;
import com.mustafa.ecommercespringboot.model.Review;
import com.mustafa.ecommercespringboot.repository.ReviewRepository;
import com.mustafa.ecommercespringboot.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ReviewDto saveReview(ReviewDto reviewDto) {
        Review review = this.reviewMapper.toReview(reviewDto);
        this.reviewRepository.save(review);
        return this.reviewMapper.toReviewDto(review);
    }

    @Override
    public ReviewDto getReviewByUserUuid(String userUuid, String productUuid) {
        Review review = this.reviewRepository.findByUserUuidAndProductUuid(userUuid,productUuid);
        if (review == null){
            throw new RuntimeException("Review not found for userUuid: " + userUuid + " and productUuid: " + productUuid);
        }
        return this.reviewMapper.toReviewDto(review);
    }

    @Override
    public List<ReviewDto> getReviewByProductUuid(String productUuid) {
        List<Review> reviewList = this.reviewRepository.findAllByProductUuid(productUuid);
        if (reviewList.isEmpty()){
            throw new RuntimeException("No reviews found for productUuid: " + productUuid);
        }
        List<ReviewDto> reviewDto  = reviewList.stream().map(review -> this.reviewMapper.toReviewDto(review))
                .collect(Collectors.toList());
        return reviewDto;
    }

    @Override
    public String updateReviewByUserUuid(String userUuid, String productUuid, ReviewDto updatedReviewDto) {
        Review existingReview = reviewRepository.findByUserUuidAndProductUuid(userUuid, productUuid);
        if (existingReview != null) {
            existingReview.setRating(updatedReviewDto.getRating());
            existingReview.setComment(updatedReviewDto.getComment());
            reviewRepository.save(existingReview);
            return "Review updated successfully";
        }
        return "Review not found";
    }

    @Override
    public String deleteReviewByUserUuid(String userUuid, String productUuid) {
        Review review = this.reviewRepository.findByUserUuidAndProductUuid(userUuid,productUuid);
        if (review == null){
            throw new RuntimeException("Review not found for userUuid: " + userUuid + " and productUuid: " + productUuid);
        }
        this.reviewRepository.delete(review);
        return "Review successfully deleted.";
    }

    @Override
    public String deleteReviewsByReviewUuid(String reviewUuid) {
        List<Review> review = this.reviewRepository.findAllByUuid(reviewUuid);
        if (review.isEmpty()){
            throw new RuntimeException("Review not found.");
        }
        this.reviewRepository.deleteAll(review);
        return "Reviews successfully deleted.";
    }

    @Override
    public String approveReview(String reviewUUID) {
        Optional<Review> optionalReview = this.reviewRepository.findByUuid(reviewUUID);
        if (optionalReview.isPresent()){
            Review review =optionalReview.get();
            review.setIsApproved(true);
            this.reviewRepository.save(review);
            return "Review has been approved successfully";
        }
        throw new RuntimeException("Review not found.");
    }

    @Override
    public List<ReviewDto> getAllReviewsByProductUUID(String productUUID) {
        return null;
    }
}
