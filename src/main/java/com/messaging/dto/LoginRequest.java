package com.messaging.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Login request; use username or email and password")
public record LoginRequest(
        @Schema(description = "Your username or email", example = "user123")
        @NotBlank String usernameOrEmail,

        @Schema(description = "Your password", example = "P@ssw0rd1")
        @NotBlank String password,

        @Schema(description = "Remember me (optional)", example = "false")
        boolean rememberMe
) {}