package io.gloe.gloe.controller;

import io.gloe.gloe.entity.Review;
import io.gloe.gloe.entity.Source;
import io.gloe.gloe.service.ReviewService;
import io.gloe.gloe.service.SourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review createdReview = reviewService.createReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Review>> createReviews(@RequestBody List<Review> reviews) {
        List<Review> createdReviews = reviewService.createReviews(reviews);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReviews);
    }
}
