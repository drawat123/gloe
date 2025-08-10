package io.gloe.gloe.repository;

import io.gloe.gloe.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SourceRepository extends JpaRepository<Source, UUID> {
}
