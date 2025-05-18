package com.messaging.controller;
import com.messaging.dto.MessageRequest;
import com.messaging.dto.MessageResponse;
import com.messaging.entity.User;
import com.messaging.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Message APIs", description = "Posting and Viewing Messages and Replies")
@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @Operation(summary = "Get Messages Tree", description = "Get all public messages in tree format (unlimited nesting, newest on top)")
    @GetMapping("/tree")
    public List<MessageResponse> tree() {
        return messageService.getTreeMessages();
    }

    @Operation(summary = "Post a Message or Comment", description = "Post a new message or reply (login required)")
    @PostMapping("/post")
    public List<MessageResponse> post(@Valid @RequestBody MessageRequest req,
                                      @AuthenticationPrincipal User user) {
        if (user == null) throw new RuntimeException("Authentication required");
        messageService.postMessage(req, user);
        return messageService.getTreeMessages();
    }
}