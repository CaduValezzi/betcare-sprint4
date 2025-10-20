package br.com.fiap.betcare.controller;

import br.com.fiap.betcare.dto.UserDTO;
import br.com.fiap.betcare.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class UserController {
    private final UserService service;
    public UserController(UserService service) { this.service = service; }

    @Operation(summary = "Get current user profile, requires authentication")
    @GetMapping
    public UserDTO me(@AuthenticationPrincipal UserDetails userDetails) {
        return service.findByUsername(userDetails.getUsername());
    }
}
