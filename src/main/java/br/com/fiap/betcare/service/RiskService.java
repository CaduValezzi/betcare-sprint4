package br.com.fiap.betcare.service;

import br.com.fiap.betcare.dto.RiskAssessmentDTO;

public interface RiskService {
    RiskAssessmentDTO assessRisk(Long userId);
}
