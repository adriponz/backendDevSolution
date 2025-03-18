package com.backend.application.product.search;

import com.backend.application.product.search.dto.ProductDTO;
import com.backend.domain.product.entity.Product;
import com.backend.domain.product.repository.SearchProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SearchSimilarProductsImplTest {
    @InjectMocks
    private SearchSimilarProductsImpl searchSimilarProducts;
    @Mock
    private SearchProductRepository searchProductRepository;

    @Test
    void should_returnResult_when_getSimilarProducts() {
        final var id = "100";
        final var product1 = new Product("1", "name1", 20D, true);
        final var product2 = new Product("2", "name2", 20D, true);
        BDDMockito.given(searchProductRepository.getSimilarIds(id)).willReturn(List.of(product1.id(), product2.id()));
        BDDMockito.given(searchProductRepository.getById(product1.id())).willReturn(Optional.of(product1));
        BDDMockito.given(searchProductRepository.getById(product2.id())).willReturn(Optional.of(product2));

        final var result = searchSimilarProducts.getSimilarProducts(id);

        final var productDTO1 = new ProductDTO("1", "name1", 20D, true);
        final var productDTO2 = new ProductDTO("2", "name2", 20D, true);
        Assertions.assertThat(result).isEqualTo(List.of(productDTO1, productDTO2));
    }

    @Test
    void should_returnResult_when_getSimilarProducts_andOneIsNotFound() {
        final var id = "100";
        final var product1 = new Product("1", "name1", 20D, true);
        final var product2 = new Product("2", "name2", 20D, true);
        BDDMockito.given(searchProductRepository.getSimilarIds(id)).willReturn(List.of(product1.id(), product2.id()));
        BDDMockito.given(searchProductRepository.getById(product1.id())).willReturn(Optional.of(product1));
        BDDMockito.given(searchProductRepository.getById(product2.id())).willReturn(Optional.empty());

        final var result = searchSimilarProducts.getSimilarProducts(id);

        final var productDTO1 = new ProductDTO("1", "name1", 20D, true);
        Assertions.assertThat(result).isEqualTo(List.of(productDTO1));
    }

}