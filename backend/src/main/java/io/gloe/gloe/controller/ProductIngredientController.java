package io.gloe.gloe.controller;

import io.gloe.gloe.entity.Category;
import io.gloe.gloe.entity.ProductIngredient;
import io.gloe.gloe.service.ProductIngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-ingredients")
public class ProductIngredientController {
    private final ProductIngredientService productIngredientService;

    public ProductIngredientController(ProductIngredientService productIngredientService) {
        this.productIngredientService = productIngredientService;
    }

    @GetMapping
    public List<ProductIngredient> getAll() {
        return productIngredientService.findAll();
    }

    @PostMapping
    public ResponseEntity<ProductIngredient> addProductIngredient(@RequestBody ProductIngredient productIngredient) {
        ProductIngredient saved = productIngredientService.saveProductIngredient(productIngredient);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}