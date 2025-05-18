package com.messaging.controller;

import com.messaging.dto.LoginRequest;
import com.messaging.dto.RegisterRequest;
import com.messaging.entity.User;
import com.messaging.service.UserService;
import com.messaging.security.JwtService;
import com.messaging.dto.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication APIs", description = "Registration and Login for Messaging Board")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Operation(summary = "Register", description = "Register a new user with username, password, email")
    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        userService.register(request);
        return "Registered successfully";
    }

    @Operation(summary = "Login", description = "Login with username or email and password")
    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody LoginRequest req,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        // Use AuthenticationManager to check credentials
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.usernameOrEmail(), req.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateToken(authentication.getName());
        return new JwtResponse(jwt);
    }

    @Operation(summary = "Get Current User Info", description = "Show username and email of logged-in user")
    @GetMapping("/me")
    public User me(Authentication auth) {
        if (auth == null || !auth.isAuthenticated())
            return null;
        return userService.findByUsernameOrEmail(auth.getName());
    }
}