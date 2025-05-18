package com.messaging.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.messaging.validation.PasswordValidator;
import com.messaging.validation.UsernameValidator;
import jakarta.validation.constraints.*;

@Schema(description = "Register request with username, password, and email")
public record RegisterRequest(
        @Schema(description = "Unique username, 5-20 letters or digits", example = "user123")
        @NotBlank @UsernameValidator String username,

        @Schema(description = "Password (8-20 chars, mix upper, lower, digit, special)", example = "P@ssw0rd1")
        @NotBlank @PasswordValidator String password,

        @Schema(description = "Valid email address", example = "test@example.com")
        @NotBlank @Email String email
) {}