package com.backend.infrastructure.persistence.repository.product;

import com.backend.domain.exception.ResourceNotFoundException;
import com.backend.domain.product.entity.Product;
import com.backend.infrastructure.persistence.client.product.ProductClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class SearchProductRepositoryImplTest {
    @InjectMocks
    private SearchProductRepositoryImpl searchProductRepository;
    @Mock
    private ProductClient productClient;

    @Test
    void should_returnEmpty_when_getById_throwsException() {
        final var product = new Product("1", "name", 20D, true);
        BDDMockito.given(productClient.getById(product.id())).willThrow(new ResourceNotFoundException("error"));

        final var result = searchProductRepository.getById(product.id());

        Assertions.assertThat(result).isEmpty();
    }

    @Test
    void should_returnEmpty_when_getById_returnsNull() {
        final var product = new Product("1", "name", 20D, true);

        final var result = searchProductRepository.getById(product.id());

        Assertions.assertThat(result).isEmpty();
    }

    @Test
    void should_returnResult_when_getById() {
        final var product = new Product("1", "name", 20D, true);
        BDDMockito.given(productClient.getById(product.id())).willReturn(product);

        final var result = searchProductRepository.getById(product.id());

        Assertions.assertThat(result).isNotEmpty().contains(product);
    }

    @Test
    void should_returnResult_when_getSimilarIds() {
        final var id = "1";
        final var similarIds = List.of("2", "3", "4");
        BDDMockito.given(productClient.getSimilarIds(id)).willReturn(similarIds);

        final var result = searchProductRepository.getSimilarIds(id);

        Assertions.assertThat(result).isEqualTo(similarIds);
    }

}