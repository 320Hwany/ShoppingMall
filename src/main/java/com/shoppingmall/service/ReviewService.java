package com.shoppingmall.service;

import com.shoppingmall.domain.Review;
import com.shoppingmall.exception.e404.PostNotFoundException;
import com.shoppingmall.repository.ReviewRepository;
import com.shoppingmall.request.ReviewRequest;
import com.shoppingmall.request.ReviewSearch;
import com.shoppingmall.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        return ReviewResponse.builder()
                .title(review.getTitle())
                .content(review.getContent())
                .rating(review.getRating())
                .build();
    }

    public ReviewResponse getReviewResponse(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        return ReviewResponse.builder()
                .title(review.getTitle())
                .content(review.getContent())
                .rating(review.getRating())
                .build();
    }

    public List<ReviewResponse> getReviewsResponse(ReviewSearch reviewSearch) {
        return reviewRepository.getReviews(reviewSearch).stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());
    }
}
