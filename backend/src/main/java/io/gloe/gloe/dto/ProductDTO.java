package io.gloe.gloe.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ProductDTO {
    private UUID id;
    private String name;
    private String brand;
    private String subcategory;
    private String description;
    private Double price;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and setters or use Lombok annotations
}