package io.gloe.gloe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product_scores")
@Getter
@Setter
public class ProductScore {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    // Link to Product by UUID
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("productScores")  // Ignore productScores inside Product to prevent recursion
    private Product product;

    @Column(name = "ingredient_score")
    private Float ingredientScore;

    @Column(name = "pregnancy_score")
    private Float pregnancyScore;

    @Column(name = "hormone_score")
    private Float hormoneScore;

    @Column(name = "sentiment_score")
    private Float sentimentScore;

    // Normalized final score between 0 and 5
    @Column(name = "final_score")
    private Float finalScore;

    @Column(name = "computed_at")
    private LocalDateTime computedAt;
}
