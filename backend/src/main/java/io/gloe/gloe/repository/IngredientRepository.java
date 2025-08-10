package io.gloe.gloe.repository;

import io.gloe.gloe.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
    @Query("SELECT i FROM Ingredient i LEFT JOIN i.synonyms s WHERE i.canonical_name = :name OR s = :name")
    Optional<Ingredient> findByNameOrSynonym(@Param("name") String name);
}