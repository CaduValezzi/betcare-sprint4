package br.com.fiap.betcare.controller;

import br.com.fiap.betcare.dto.RiskAssessmentDTO;
import br.com.fiap.betcare.service.RiskService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/risk")
public class RiskController {
    private final RiskService riskService;
    public RiskController(RiskService riskService) { this.riskService = riskService; }

    @Operation(summary = "Assess risk for given user id (admins) or self)")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/assess/{userId}")
    public ResponseEntity<RiskAssessmentDTO> assess(@PathVariable Long userId) {
        var dto = riskService.assessRisk(userId);
        return ResponseEntity.ok(dto);
    }
}
