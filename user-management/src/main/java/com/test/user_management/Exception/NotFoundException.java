package com.test.user_management.Exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message + " not found");
    }
}
