package com.messaging.service;

import com.messaging.dto.MessageRequest;
import com.messaging.dto.MessageResponse;
import com.messaging.entity.Message;
import com.messaging.entity.User;
import com.messaging.repository.MessageRepository;
import com.messaging.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public void postMessage(MessageRequest req, User author) {
        Message parent = null;
        if (req.parentId() != null) {
            parent = messageRepository.findById(req.parentId())
                    .orElseThrow(() -> new ApiException("Parent message not found"));
        }
        Message message = Message.builder()
                .content(req.content())
                .createdAt(Instant.now())
                .user(author)
                .parent(parent)
                .build();
        messageRepository.save(message);
    }

    public List<MessageResponse> getTreeMessages() {
        // 1. Fetch all in a single query (avoid N+1)
        List<Message> allMsgs = messageRepository.findAllByOrderByCreatedAtDesc();

        // 2. Build id -> msg map
        Map<Long, MessageResponse> map = new LinkedHashMap<>();
        for (Message m : allMsgs) {
            MessageResponse dto = MessageResponse.builder()
                    .id(m.getId())
                    .content(m.getContent())
                    .createdAt(m.getCreatedAt())
                    .username(m.getUser().getUsername())
                    .children(new ArrayList<>())
                    .build();
            map.put(m.getId(), dto);
        }
        // 3. Parent to children mapping in-memory
        List<MessageResponse> roots = new ArrayList<>();
        for (Message m : allMsgs) {
            if (m.getParent() != null) {
                MessageResponse parent = map.get(m.getParent().getId());
                parent.getChildren().add(0, map.get(m.getId())); // insert newest at top
            } else {
                roots.add(map.get(m.getId()));
            }
        }
        return roots;
    }
}
