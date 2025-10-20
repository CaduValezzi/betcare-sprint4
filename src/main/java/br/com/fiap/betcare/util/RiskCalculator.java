package br.com.fiap.betcare.util;

import br.com.fiap.betcare.entity.Bet;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

public class RiskCalculator {
    public static double calculate(List<Bet> recentBets) {
        if (recentBets == null || recentBets.isEmpty()) return 0.0;

        BigDecimal total = BigDecimal.ZERO;
        for (Bet b : recentBets) total = total.add(b.getAmount());

        double amountScore = Math.min(1.0, total.doubleValue() / 1000.0); // 1000 BRL -> score 1.0
        double freqScore = Math.min(1.0, recentBets.size() / 20.0); // 20 bets -> score 1.0

        double timePressure = 0.0;
        for (int i = 1; i < recentBets.size(); i++) {
            Duration d = Duration.between(recentBets.get(i-1).getPlacedAt(), recentBets.get(i).getPlacedAt());
            if (d.toHours() < 1) timePressure += 0.1;
        }
        timePressure = Math.min(1.0, timePressure);

        double score = 0.6 * amountScore + 0.3 * freqScore + 0.1 * timePressure;
        return Math.max(0.0, Math.min(1.0, score));
    }
}
