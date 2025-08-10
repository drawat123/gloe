package io.gloe.gloe.service;

import io.gloe.gloe.entity.Review;

import java.util.List;

public interface ReviewService {
    Review createReview(Review review);

    List<Review> createReviews(List<Review> reviews);
}
