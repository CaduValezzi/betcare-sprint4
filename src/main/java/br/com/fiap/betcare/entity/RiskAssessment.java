package br.com.fiap.betcare.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "risk_assessments")
public class RiskAssessment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Double score; // 0.0 - 1.0
    private String level; // LOW, MEDIUM, HIGH
    private Instant assessedAt;

    private String notes;

    public RiskAssessment() {}
    public RiskAssessment(User user, Double score, String level, Instant assessedAt, String notes) {
        this.user = user; this.score = score; this.level = level; this.assessedAt = assessedAt; this.notes = notes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    public Instant getAssessedAt() { return assessedAt; }
    public void setAssessedAt(Instant assessedAt) { this.assessedAt = assessedAt; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
