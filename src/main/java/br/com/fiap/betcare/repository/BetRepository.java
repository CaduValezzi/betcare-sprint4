package br.com.fiap.betcare.repository;

import br.com.fiap.betcare.entity.Bet;
import br.com.fiap.betcare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findByUserAndPlacedAtAfter(User user, Instant after);
}
