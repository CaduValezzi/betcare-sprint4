package br.com.fiap.betcare.repository;

import br.com.fiap.betcare.entity.RiskAssessment;
import br.com.fiap.betcare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RiskAssessmentRepository extends JpaRepository<RiskAssessment, Long> {
    Optional<RiskAssessment> findTopByUserOrderByAssessedAtDesc(User user);
}
