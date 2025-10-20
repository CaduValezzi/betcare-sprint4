package br.com.fiap.betcare.service.impl;

import br.com.fiap.betcare.dto.RiskAssessmentDTO;
import br.com.fiap.betcare.entity.Bet;
import br.com.fiap.betcare.entity.RiskAssessment;
import br.com.fiap.betcare.repository.BetRepository;
import br.com.fiap.betcare.repository.RiskAssessmentRepository;
import br.com.fiap.betcare.repository.UserRepository;
import br.com.fiap.betcare.util.RiskCalculator;
import br.com.fiap.betcare.entity.User;
import br.com.fiap.betcare.service.RiskService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RiskServiceImpl implements RiskService {
    private final UserRepository userRepository;
    private final BetRepository betRepository;
    private final RiskAssessmentRepository riskRepo;

    public RiskServiceImpl(UserRepository userRepository, BetRepository betRepository, RiskAssessmentRepository riskRepo) {
        this.userRepository = userRepository;
        this.betRepository = betRepository;
        this.riskRepo = riskRepo;
    }

    @Override
    public RiskAssessmentDTO assessRisk(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Instant since = Instant.now().minus(7, ChronoUnit.DAYS);
        List<Bet> recent = betRepository.findByUserAndPlacedAtAfter(user, since);

        double score = RiskCalculator.calculate(recent);
        String level = score >= 0.7 ? "HIGH" : (score >= 0.4 ? "MEDIUM" : "LOW");
        String notes = "Heuristics: amount/frequency/time-pressure";

        RiskAssessment ra = new RiskAssessment(user, score, level, Instant.now(), notes);
        riskRepo.save(ra);

        return new RiskAssessmentDTO(score, level, ra.getAssessedAt(), notes);
    }
}
