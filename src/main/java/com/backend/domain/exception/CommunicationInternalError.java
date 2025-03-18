package com.backend.domain.exception;

public class CommunicationInternalError extends RuntimeException {

    public CommunicationInternalError(String message) {
        super(message);
    }
}
