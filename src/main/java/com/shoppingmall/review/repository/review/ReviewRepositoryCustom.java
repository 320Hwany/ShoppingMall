package com.shoppingmall.review.repository.review;

import com.shoppingmall.review.domain.Review;
import com.shoppingmall.review.dto.request.ReviewSearch;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> getReviewsByLatest(ReviewSearch reviewSearch);

    List<Review> getReviewsByHighRating(ReviewSearch reviewSearch);

    List<Review> getReviewsByLowRating(ReviewSearch reviewSearch);
}