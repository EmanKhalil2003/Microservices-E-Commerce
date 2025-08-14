package com.project.ecommerce.controller;

import com.project.ecommerce.dto.ReviewRequest;
import com.project.ecommerce.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buyer")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/reviews")
    public ResponseEntity<?> createReview(@Valid @RequestBody ReviewRequest reviewRequest, @RequestHeader("X-User-Id") Long userId) {

        // Submit the review
        reviewService.submitReview(userId, reviewRequest);

        return ResponseEntity.ok("Review submitted successfully!");
    }
}
