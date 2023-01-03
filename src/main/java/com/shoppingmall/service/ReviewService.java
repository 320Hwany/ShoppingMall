package com.shoppingmall.service;

import com.shoppingmall.domain.Review;
import com.shoppingmall.exception.e404.ReviewNotFoundException;
import com.shoppingmall.repository.review.ReviewRepository;
import com.shoppingmall.request.review.ReviewSave;
import com.shoppingmall.request.review.ReviewSearch;
import com.shoppingmall.request.review.ReviewUpdate;
import com.shoppingmall.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewResponse reviewSave(ReviewSave reviewSave) {
        Review review = Review.builder()
                .title(reviewSave.getTitle())
                .content(reviewSave.getContent())
                .rating(reviewSave.getRating())
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
                .orElseThrow(ReviewNotFoundException::new);

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

    @Transactional
    public ReviewResponse updateReview(Long id, ReviewUpdate reviewUpdate) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(ReviewNotFoundException::new);
        review.update(reviewUpdate);
        return new ReviewResponse(review);
    }

    @Transactional
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(ReviewNotFoundException::new);
        reviewRepository.delete(review);
    }
}
