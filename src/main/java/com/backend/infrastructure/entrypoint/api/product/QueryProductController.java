package com.backend.infrastructure.entrypoint.api.product;

import com.backend.application.product.search.SearchSimilarProducts;
import com.backend.application.product.search.dto.ProductDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
final class QueryProductController {
    private final SearchSimilarProducts searchSimilarProducts;

    QueryProductController(SearchSimilarProducts searchSimilarProducts) {
        this.searchSimilarProducts = searchSimilarProducts;
    }

    @GetMapping("/{productId}/similar")
    public List<ProductDTO> getSimilarProducts(@PathVariable("productId") String productId) {
        return searchSimilarProducts.getSimilarProducts(productId);
    }
}
