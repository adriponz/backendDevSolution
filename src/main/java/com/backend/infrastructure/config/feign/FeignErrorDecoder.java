package com.backend.infrastructure.config.feign;

import com.backend.domain.exception.CommunicationInternalError;
import com.backend.domain.exception.ResourceNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new ResourceNotFoundException("Resource not found");
        } else {
            return new CommunicationInternalError("Internal error");
        }
    }
}
