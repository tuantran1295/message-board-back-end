package com.messaging.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Post a new message or reply")
public record MessageRequest(
        @Schema(description = "Parent message ID (null for root message)", example = "1")
        Long parentId,

        @Schema(description = "Message content, 3 to 200 characters", example = "Hello everyone!")
        @NotBlank @Size(min = 3, max = 200, message = "Message must be 3-200 characters")
        String content
) {}
