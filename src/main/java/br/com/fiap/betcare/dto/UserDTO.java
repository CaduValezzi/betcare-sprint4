package br.com.fiap.betcare.dto;

import java.util.Set;

public record UserDTO(Long id, String username, Set<String> roles) {}
