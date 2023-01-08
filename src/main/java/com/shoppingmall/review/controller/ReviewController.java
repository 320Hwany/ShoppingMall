package com.shoppingmall.review.controller;

import com.shoppingmall.review.dto.request.ReviewSave;
import com.shoppingmall.review.dto.request.ReviewSearch;
import com.shoppingmall.review.dto.request.ReviewUpdate;
import com.shoppingmall.review.dto.response.ReviewResponse;
import com.shoppingmall.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/review-save")
    public ResponseEntity<ReviewResponse> saveReview(@RequestBody @Valid ReviewSave reviewSave) {
        ReviewResponse reviewResponse = reviewService.saveReview(reviewSave);
        return ResponseEntity.ok(reviewResponse);
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<ReviewResponse> getReview(@PathVariable Long reviewId) {
        ReviewResponse reviewResponse = reviewService.getReviewResponse(reviewId);
        return ResponseEntity.ok(reviewResponse);
    }

    @GetMapping("/reviews-latest")
    public ResponseEntity<List<ReviewResponse>> getReviewsByLatest(@ModelAttribute ReviewSearch reviewSearch) {
        List<ReviewResponse> reviewsResponse = reviewService.getReviewsResponseByLatest(reviewSearch);
        return ResponseEntity.ok(reviewsResponse);
    }

    @GetMapping("/reviews-lowRating")
    public ResponseEntity<List<ReviewResponse>> getReviewsByLowRating(@ModelAttribute ReviewSearch reviewSearch) {
        List<ReviewResponse> reviewsResponse = reviewService.getReviewsResponseByLowRating(reviewSearch);
        return ResponseEntity.ok(reviewsResponse);
    }

    @GetMapping("/reviews-highRating")
    public ResponseEntity<List<ReviewResponse>> getReviewsByHighRating(@ModelAttribute ReviewSearch reviewSearch) {
        List<ReviewResponse> reviewsResponse = reviewService.getReviewsResponseByHighRating(reviewSearch);
        return ResponseEntity.ok(reviewsResponse);
    }

    @PatchMapping("/review/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable Long reviewId,
                                                       @RequestBody @Valid ReviewUpdate reviewUpdate) {
        ReviewResponse reviewResponse = reviewService.updateReview(reviewId, reviewUpdate);
        return ResponseEntity.ok(reviewResponse);
    }

    @DeleteMapping("/review/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
