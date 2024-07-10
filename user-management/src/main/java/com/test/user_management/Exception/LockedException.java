package com.test.user_management.Exception;

import lombok.Getter;

@Getter
public class LockedException extends RuntimeException {
    private String code;

    public LockedException(String message) {
        super(message);
        this.code = "1002";
    }
}
