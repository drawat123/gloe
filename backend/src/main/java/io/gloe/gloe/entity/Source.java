package io.gloe.gloe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sources")
@Getter
@Setter
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    // Link to the Product entity using UUID as primary key
    // many sources → one product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnoreProperties("sources")  // Ignore the 'sources' inside Product to break cycle
    private Product product;

    // Name of the source (e.g., Nykaa, Amazon, Myntra)
    @Column(name = "source_name", length = 100, nullable = false)
    private String sourceName;

    // Product ID on the source platform
    @Column(name = "source_product_id", length = 255)
    private String sourceProductId;

    // Price of the product on this source
    @Column(name = "price")
    private Double price;

    // Link to the product page on the source
    @Column(name = "link", length = 1000)
    private String link;

    // Timestamp when the data was scraped
    @Column(name = "scraped_at")
    private LocalDateTime scrapedAt;

    // Number of reviews on the source
    @Column(name = "review_count")
    private Integer reviewCount;

    // Average rating on the source
    @Column(name = "rating")
    private Double rating;

    /**
     * Raw JSON response stored as jsonb in DB from source (e.g., Nykaa).
     * Stored as PostgreSQL jsonb for efficient querying and flexibility.
     * Example:
     * {
     * "nykaa": { "product_id": "12345", "price": 399, "rating": 4.5 },
     * }
     */
    @Column(name = "raw_response", columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    private String rawResponse;

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("source")  // Ignore 'source' inside Review to prevent recursion
    private List<Review> reviews = new ArrayList<>();
}
