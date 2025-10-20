package br.com.fiap.betcare.dto;

import java.time.Instant;

public record RiskAssessmentDTO(Double score, String level, Instant assessedAt, String notes) {}
