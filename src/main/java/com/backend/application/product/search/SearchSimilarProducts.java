package com.backend.application.product.search;

import com.backend.application.product.search.dto.ProductDTO;

import java.util.List;

public interface SearchSimilarProducts {
    List<ProductDTO> getSimilarProducts(String id);
}
