package com.mustafa.ecommercespringboot.mapper;

import com.mustafa.ecommercespringboot.dto.ReviewDto;
import com.mustafa.ecommercespringboot.model.Review;

public interface ReviewMapper {
    Review toReview(ReviewDto reviewDto);
    ReviewDto toReviewDto(Review review);
}
