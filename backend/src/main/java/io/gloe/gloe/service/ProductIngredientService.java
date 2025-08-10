package io.gloe.gloe.service;

import io.gloe.gloe.entity.ProductIngredient;

import java.util.List;

public interface ProductIngredientService {
    ProductIngredient saveProductIngredient(ProductIngredient productIngredient);

    List<ProductIngredient> findAll();
}
