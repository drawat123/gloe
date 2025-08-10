package io.gloe.gloe.service;

import io.gloe.gloe.entity.ProductScore;
import io.gloe.gloe.repository.ProductScoreRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductScoreServiceImpl implements ProductScoreService {
    private final ProductScoreRepository productScoreRepository;

    public ProductScoreServiceImpl(ProductScoreRepository productScoreRepository) {
        this.productScoreRepository = productScoreRepository;
    }

    @Override
    public ProductScore createProductScore(ProductScore productScore) {
        return productScoreRepository.save(productScore);
    }
}
