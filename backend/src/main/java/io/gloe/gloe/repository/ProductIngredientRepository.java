package io.gloe.gloe.repository;

import io.gloe.gloe.entity.Product;
import io.gloe.gloe.entity.ProductIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductIngredientRepository extends JpaRepository<ProductIngredient, UUID> {
    void deleteAllByProduct(Product savedProduct);
}