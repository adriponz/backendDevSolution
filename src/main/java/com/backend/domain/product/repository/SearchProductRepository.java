package com.backend.domain.product.repository;

import com.backend.domain.product.entity.Product;

import java.util.List;
import java.util.Optional;

public interface SearchProductRepository {

    Optional<Product> getById(String id);
    List<String> getSimilarIds(String id);
}
