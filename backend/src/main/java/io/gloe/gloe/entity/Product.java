package io.gloe.gloe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    /**
     * The category this product belongs to (e.g., Face, Body, Hair).
     * Mapped as a Many-to-One relationship: many products can share one category.
     * Stored in the database as a foreign key column "category_id" in the products table,
     * referencing the "id" column in the categories table.
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("products")  // Ignore the 'products' inside category to break cycle
    private Category category;

    /**
     * More specific grouping within a category (e.g., Sunscreen, Moisturizer, Serum).
     * Stored as plain text for flexibility — no separate subcategory table in MVP.
     * Useful for filtering products within the same category.
     */
    @Column(name = "subcategory")
    private String subcategory;

    @Column(name = "description", length = 5000)
    private String description;

    /**
     * Stores the original, unprocessed ingredient list exactly as scraped or entered.
     * This is the source-of-truth for re-parsing, debugging, and manual verification.
     * Example: "Aqua, Ethylhexyl Methoxycinnamate, Butyl Methoxydibenzoylmethane..."
     * Better to keep database column names in snake_case for readability.
     */
    @Column(columnDefinition = "TEXT", name = "ingredients_raw")
    private String ingredientsRaw;

    @Column(name = "image_url")
    private String imageUrl;

    /**
     * Product price stored in Indian Rupees (INR).
     * <p>
     * This is the current single-currency setup.
     * Future versions may support multi-currency pricing per region.
     */
    @Column(name = "price")
    private Double price;

    @CreationTimestamp // @CreationTimestamp automatically sets the creation time when the entity is persisted.
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp // @UpdateTimestamp automatically updates the updatedAt every time the entity is updated.
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * The list of ingredients associated with this product.
     * <p>
     * This defines a one-to-many relationship where one product can have multiple
     * product ingredient entries. The 'mappedBy' attribute indicates that the
     * 'product' field in ProductIngredient owns the relationship.
     * CascadeType.ALL ensures that any changes to the product (save, update, delete)
     * are propagated to its associated ingredients.
     * “The ProductIngredient entity has a field called product that links back to this Product. This side is inverse (non-owning).”
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("product")  // Ignore the 'product' inside ProductIngredient to break cycle
    private List<ProductIngredient> productIngredients = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("product")  // Ignore the 'product' inside Source to break cycle
    private List<Source> sources = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("product")
    private List<ProductScore> productScores = new ArrayList<>();
}
