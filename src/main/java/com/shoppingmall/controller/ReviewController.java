package com.shoppingmall.controller;

import com.shoppingmall.request.ReviewRequest;
import com.shoppingmall.request.ReviewSearch;
import com.shoppingmall.response.ReviewResponse;
import com.shoppingmall.service.ReviewService;
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
    public ResponseEntity<ReviewResponse> reviewSave(@RequestBody @Valid ReviewRequest reviewRequest) {
        ReviewResponse reviewResponse = reviewService.reviewSave(reviewRequest);
        return ResponseEntity.ok(reviewResponse);
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<ReviewResponse> getReview(@PathVariable Long reviewId) {
        ReviewResponse reviewResponse = reviewService.getReviewResponse(reviewId);
        return ResponseEntity.ok(reviewResponse);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewResponse>> getReviews(@ModelAttribute ReviewSearch reviewSearch) {
        List<ReviewResponse> reviewsResponse = reviewService.getReviewsResponse(reviewSearch);
        return ResponseEntity.ok(reviewsResponse);
    }
}
