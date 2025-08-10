package io.gloe.gloe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "product_ingredients")
@Getter
@Setter
public class ProductIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    // The product to which this ingredient belongs (foreign key)
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("productIngredients")  // Ignore the 'productIngredients' inside Product to break cycle
    private Product product;

    // The ingredient referenced by this ProductIngredient (foreign key)
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    // Original text description or notes about the ingredient for this product, e.g., quantity or preparation details
    @Column(length = 500)
    private String rawText; // original text from source
}