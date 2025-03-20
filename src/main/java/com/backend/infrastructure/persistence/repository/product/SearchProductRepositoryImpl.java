package com.backend.infrastructure.persistence.repository.product;

import com.backend.domain.product.entity.Product;
import com.backend.domain.product.repository.SearchProductRepository;
import com.backend.infrastructure.persistence.client.product.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
class SearchProductRepositoryImpl implements SearchProductRepository {
    private final ProductClient productClient;

    SearchProductRepositoryImpl(ProductClient productClient) {
        this.productClient = productClient;
    }

    @Override
    @Cacheable(cacheNames = "products", key = "#id")
    public Optional<Product> getById(String id) {
        try {
            return Optional.ofNullable(productClient.getById(id));
        } catch (Exception ex) {
            log.error(String.format("Error trying to get product with id=%s. %s", id, ex.getMessage()), ex);
            return Optional.empty();
        }
    }

    @Override
    public List<String> getSimilarIds(String id) {
        try {
            return productClient.getSimilarIds(id);
        } catch (Exception ex) {
            log.error(String.format("Error trying to get similars of product with id=%s. %s", id, ex.getMessage()), ex);
            return Collections.emptyList();
        }
    }
}
