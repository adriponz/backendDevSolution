package com.backend.application.product.search;

import com.backend.domain.product.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductMapperTest {

    @Test
    void should_transformToProductDTO_when_fromProduct() {
        final var product = new Product("1", "name", 20D, true);

        final var result = ProductMapper.toDTO(product);

        Assertions.assertThat(result.getId()).isEqualTo(product.id());
        Assertions.assertThat(result.getName()).isEqualTo(product.name());
        Assertions.assertThat(result.getPrice()).isEqualTo(product.price());
        Assertions.assertThat(result.isAvailability()).isEqualTo(product.availability());
    }
}