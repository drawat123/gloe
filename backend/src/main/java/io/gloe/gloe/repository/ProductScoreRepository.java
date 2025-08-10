package io.gloe.gloe.repository;

import io.gloe.gloe.entity.ProductScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductScoreRepository extends JpaRepository<ProductScore, UUID> {
}
