package com.shoppingmall.service;

import com.shoppingmall.domain.Review;
import com.shoppingmall.repository.ReviewRepository;
import com.shoppingmall.request.ReviewRequest;
import com.shoppingmall.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewResponse reviewSave(ReviewRequest reviewRequest) {
        Review review = Review.builder()
                .title(reviewRequest.getTitle())
                .content(reviewRequest.getContent())
                .rating(reviewRequest.getRating())
                .build();

        reviewRepository.save(review);

        ReviewResponse reviewResponse = ReviewResponse.builder()
                .title(review.getTitle())
                .content(review.getContent())
                .rating(review.getRating())
                .build();

        return reviewResponse;
    }
}
