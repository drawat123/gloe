package io.gloe.gloe.mapper;

import io.gloe.gloe.dto.ProductDTO;
import io.gloe.gloe.entity.Product;

public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        if (product == null) return null;

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setBrand(product.getBrand());
        dto.setSubcategory(product.getSubcategory());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setImageUrl(product.getImageUrl());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }

    public static Product toEntity(ProductDTO dto) {
        if (dto == null) return null;

        Product product = new Product();
        product.setId(dto.getId());  // Usually null for new entities
        product.setName(dto.getName());
        product.setBrand(dto.getBrand());
        product.setSubcategory(dto.getSubcategory());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImageUrl(dto.getImageUrl());
        // For createdAt and updatedAt, let Hibernate handle these automatically
        return product;
    }
}