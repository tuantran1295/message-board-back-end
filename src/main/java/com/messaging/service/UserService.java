package com.messaging.service;

import com.messaging.dto.RegisterRequest;
import com.messaging.entity.User;
import com.messaging.repository.UserRepository;
import com.messaging.exception.ApiException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void logUserCount() {
        log.info("Users in DB: {}", userRepository.count());
    }

    public void register(RegisterRequest req) {
        if (userRepository.existsByUsername(req.username())) {
            throw new ApiException("Username already exists");
        }
        if (userRepository.existsByEmail(req.email())) {
            throw new ApiException("Email already used");
        }
        User user = User.builder()
                .username(req.username())
                .email(req.email())
                .password(passwordEncoder.encode(req.password()))
                .build();
        userRepository.save(user);
    }

    public User login(String usernameOrEmail, String password) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new ApiException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ApiException("Invalid credentials");
        }
        return user;
    }

    @Override
    public User loadUserByUsername(String usernameOrEmail) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new ApiException("User not found"));
    }

    public User findByUsernameOrEmail(String userOrMail) {
        return userRepository.findByUsernameOrEmail(userOrMail, userOrMail)
                .orElseThrow(() -> new ApiException("User not found"));
    }
}
