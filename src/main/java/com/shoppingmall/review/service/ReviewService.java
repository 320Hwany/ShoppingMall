package com.shoppingmall.review.service;

import com.shoppingmall.review.domain.Review;
import com.shoppingmall.review.exception.ReviewNotFoundException;
import com.shoppingmall.review.repository.review.ReviewRepository;
import com.shoppingmall.review.dto.request.ReviewSave;
import com.shoppingmall.review.dto.request.ReviewSearch;
import com.shoppingmall.review.dto.request.ReviewUpdate;
import com.shoppingmall.review.dto.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewResponse reviewSave(ReviewSave reviewSave) {
        Review review = reviewSave.toEntity();
        reviewRepository.save(review);
        return new ReviewResponse(review);
    }

    public ReviewResponse getReviewResponse(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(ReviewNotFoundException::new);
        return new ReviewResponse(review);
    }

    public List<ReviewResponse> getReviewsResponse(ReviewSearch reviewSearch) {
        return reviewRepository.getReviews(reviewSearch).stream()
                .map(ReviewResponse::new)
                .collect(toList());
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
