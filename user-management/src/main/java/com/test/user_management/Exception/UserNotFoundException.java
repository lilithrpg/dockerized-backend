package com.test.user_management.Exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends NotFoundException {
    private String code;

    public UserNotFoundException() {
        super("User");
        this.code = "1001";
    }
}
