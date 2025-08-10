package io.gloe.gloe.service;

import io.gloe.gloe.entity.Ingredient;
import io.gloe.gloe.entity.Product;
import io.gloe.gloe.entity.ProductIngredient;
import io.gloe.gloe.repository.IngredientRepository;
import io.gloe.gloe.repository.ProductIngredientRepository;
import io.gloe.gloe.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;
    private final ProductIngredientRepository productIngredientRepository;

    public ProductServiceImpl(ProductRepository productRepository, IngredientRepository ingredientRepository, ProductIngredientRepository productIngredientRepository) {
        this.productRepository = productRepository;
        this.ingredientRepository = ingredientRepository;
        this.productIngredientRepository = productIngredientRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }

    //@Transactional JpaRepository provided this
    @Override
    public Product save(Product product) {
        return saveProductWithIngredients(product);
    }

    @Transactional
    public Product saveProductWithIngredients(Product product) {
        // Save product first
        Product savedProduct = productRepository.save(product);

        // Clear existing ProductIngredients if any
        productIngredientRepository.deleteAllByProduct(savedProduct);

        // Parse raw ingredients, split by commas
        List<String> rawIngredients = Arrays.stream(product.getIngredientsRaw().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        for (String rawText : rawIngredients) {
            // Find or create Ingredient
            Optional<Ingredient> ingredient = ingredientRepository.findByNameOrSynonym(rawText);

            if (ingredient.isPresent()) {
                // Create ProductIngredient
                ProductIngredient pi = new ProductIngredient();
                pi.setProduct(savedProduct);
                pi.setIngredient(ingredient.get());
                pi.setRawText(rawText);
                productIngredientRepository.save(pi);
            }
        }

        // Optional: refresh product to include ingredients list
        return savedProduct;
    }
}
