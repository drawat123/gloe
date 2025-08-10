package io.gloe.gloe.service;

import io.gloe.gloe.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(UUID id);

    Product save(Product product);
}
