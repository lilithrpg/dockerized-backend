package com.test.user_management.Exception;

import lombok.Getter;

@Getter
public class DuplicateException extends RuntimeException {
    private String code;

    public DuplicateException(String message) {
        super(message);
        this.code = code;
    }
}
