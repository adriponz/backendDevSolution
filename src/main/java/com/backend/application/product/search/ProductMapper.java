package com.backend.application.product.search;

import com.backend.application.product.search.dto.ProductDTO;
import com.backend.domain.product.entity.Product;

final class ProductMapper {

    static ProductDTO toDTO(Product product) {
        return new ProductDTO(product.id(), product.name(), product.price(), product.availability());
    }
}
