package io.gloe.gloe.service;

import io.gloe.gloe.entity.Review;
import io.gloe.gloe.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> createReviews(List<Review> reviews) {
        return reviewRepository.saveAll(reviews);
    }
}
