package com.backend.infrastructure.config.feign;

import com.backend.infrastructure.persistence.client.product.ProductClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class FeignConfig {
    @Value("${product.api.base.url}")
    private String baseUrl;
    @Value("${feign.client.connection.timeout.millis}")
    private Long connectionTimeoutMillis;
    @Value("${feign.client.read.timeout.millis}")
    private Long readTimeoutMillis;

    @Bean
    JacksonDecoder jacksonDecoder(ObjectMapper objectMapper) {
        return new JacksonDecoder(objectMapper);
    }

    @Bean
    JacksonEncoder jacksonEncoder(ObjectMapper objectMapper) {
        return new JacksonEncoder(objectMapper);
    }

    @Bean
    ProductClient getProductClient(JacksonDecoder jacksonDecoder, JacksonEncoder jacksonEncoder, FeignErrorDecoder errorDecoder) {
        return Feign.builder()
                .options(new Request.Options(
                        Duration.ofMillis(connectionTimeoutMillis),
                        Duration.ofMillis(readTimeoutMillis),
                        false))
                .encoder(jacksonEncoder)
                .decoder(jacksonDecoder)
                .errorDecoder(errorDecoder)
                .retryer(Retryer.NEVER_RETRY)
                .target(ProductClient.class, baseUrl);
    }
}
