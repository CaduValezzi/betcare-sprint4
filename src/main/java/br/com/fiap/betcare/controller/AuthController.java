package br.com.fiap.betcare.controller;

import br.com.fiap.betcare.dto.AuthRequest;
import br.com.fiap.betcare.dto.AuthResponse;
import br.com.fiap.betcare.dto.UserDTO;
import br.com.fiap.betcare.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService service;
    public AuthController(UserService service) { this.service = service; }

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody AuthRequest req) {
        var user = service.register(req.username(), req.password());
        return ResponseEntity.ok(new UserDTO(user.getId(), user.getUsername(), user.getRoles()));
    }

    @Operation(summary = "Login and receive JWT")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        String token = service.login(req.username(), req.password());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
