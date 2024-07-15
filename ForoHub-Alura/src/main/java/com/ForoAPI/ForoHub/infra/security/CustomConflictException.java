package com.ForoAPI.ForoHub.infra.security;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

public class CustomConflictException extends RuntimeException{
    private final HttpStatus status;

    public CustomConflictException(String message) {
        super(message);
        this.status = CONFLICT;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
