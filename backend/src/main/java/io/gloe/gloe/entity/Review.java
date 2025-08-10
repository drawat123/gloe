package io.gloe.gloe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reviews")
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    // Many reviews belong to one source
    @ManyToOne
    @JoinColumn(name = "source_id")
    @JsonIgnoreProperties("reviews")  // Ignore reviews inside Source to prevent recursion
    private Source source;

    // Reviewer name, nullable
    @Column(name = "reviewer")
    private String reviewer;

    // Rating given in the review
    @Column(name = "rating")
    private Double rating;

    // Review text content
    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    // Sentiment score of the review (e.g., -1 to 1)
    @Column(name = "sentiment_score")
    private Double sentimentScore;

    // When the review was scraped
    @Column(name = "scraped_at")
    private LocalDateTime scrapedAt;
}
