package br.com.fiap.betcare.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record BetDTO(Long id, BigDecimal amount, Instant placedAt) {}
