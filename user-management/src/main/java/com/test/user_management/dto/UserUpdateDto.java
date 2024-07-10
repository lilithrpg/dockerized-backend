package com.test.user_management.dto;

import lombok.Data;
import jakarta.validation.constraints.NotEmpty;

@Data
public class UserUpdateDto {

    @NotEmpty(message = "Id is required")
    private Long id;

    @NotEmpty
    private UserDetailsDto userDetails;
}
