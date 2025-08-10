package io.gloe.gloe.service;

import io.gloe.gloe.entity.Category;
import io.gloe.gloe.entity.Product;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);

    List<Category> findAll();
}
