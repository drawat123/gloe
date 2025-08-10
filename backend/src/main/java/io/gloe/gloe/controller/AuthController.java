package io.gloe.gloe.controller;

import io.gloe.gloe.dto.AuthRequest;
import io.gloe.gloe.entity.User;
import io.gloe.gloe.service.UserService;
import io.gloe.gloe.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final JwtUtil jwtUtil;

    private final UserService userService;

    public AuthController(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        try {
            // You can add more validations here (email format, password strength etc.)
            User user = userService.registerUser(request.getEmail(), request.getPassword(), User.Role.USER);
            return ResponseEntity.ok(Map.of("message", "User registered successfully", "userId", user.getId()));
        } catch (RuntimeException ex) {
            // e.g. email already registered
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest request) {
        String email = request.getEmail();

        if (!userService.validateCredentials(email, request.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(email);

        return ResponseEntity.ok(Map.of("token", token));
    }
}