package com.messaging.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Message response with nested children replies")
public class MessageResponse {
    @Schema(description = "Message ID", example = "5")
    private Long id;

    @Schema(description = "Content of the message", example = "This is a reply!")
    private String content;

    @Schema(description = "Username of the message author", example = "user123")
    private String username;

    @Schema(description = "Creation date/time (ISO 8601)", example = "2024-05-25T14:54:35.576Z")
    private Instant createdAt;

    @Schema(description = "List of child (reply) messages")
    private List<MessageResponse> children;
}