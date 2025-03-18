package com.backend.application.product.search;

import com.backend.application.product.search.dto.ProductDTO;
import com.backend.domain.product.repository.SearchProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
final class SearchSimilarProductsImpl implements SearchSimilarProducts {
    private final SearchProductRepository searchProductRepository;

    SearchSimilarProductsImpl(SearchProductRepository searchProductRepository) {
        this.searchProductRepository = searchProductRepository;
    }

    @Override
    public List<ProductDTO> getSimilarProducts(String id) {
        return searchProductRepository.getSimilarIds(id).stream()
                .map(searchProductRepository::getById)
                .flatMap(Optional::stream)
                .map(ProductMapper::toDTO)
                .toList();
    }
}
