package com.shoppingmall.controller;

import com.shoppingmall.request.ReviewRequest;
import com.shoppingmall.response.ReviewResponse;
import com.shoppingmall.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/review-save")
    public ResponseEntity<ReviewResponse> reviewSave(@RequestBody @Valid ReviewRequest reviewRequest) {
        ReviewResponse reviewResponse = reviewService.reviewSave(reviewRequest);
        return ResponseEntity.ok(reviewResponse);
    }
}
