package io.gloe.gloe.service;

import io.gloe.gloe.entity.ProductIngredient;
import io.gloe.gloe.repository.ProductIngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductIngredientServiceImpl implements ProductIngredientService {
    private final ProductIngredientRepository productIngredientRepository;

    public ProductIngredientServiceImpl(ProductIngredientRepository productIngredientRepository) {
        this.productIngredientRepository = productIngredientRepository;
    }

    @Override
    public ProductIngredient saveProductIngredient(ProductIngredient productIngredient) {
        return productIngredientRepository.save(productIngredient);
    }

    @Override
    public List<ProductIngredient> findAll() {
        return productIngredientRepository.findAll();
    }
}
