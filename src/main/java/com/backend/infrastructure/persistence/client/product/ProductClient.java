package com.backend.infrastructure.persistence.client.product;

import com.backend.domain.product.entity.Product;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface ProductClient {

    @RequestLine("GET /product/{productId}")
    Product getById(@Param String productId);

    @RequestLine("GET /product/{productId}/similarids")
    List<String> getSimilarIds(@Param String productId);
}
