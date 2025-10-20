package com.example.betcare.unit;

import com.example.betcare.repository.BetRepository;
import com.example.betcare.repository.RiskAssessmentRepository;
import com.example.betcare.repository.UserRepository;
import com.example.betcare.service.impl.RiskServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class RiskServiceTest {
    @Mock UserRepository userRepo;
    @Mock BetRepository betRepo;
    @Mock RiskAssessmentRepository riskRepo;
    RiskServiceImpl service;

    @BeforeEach
    void setup() { MockitoAnnotations.openMocks(this); service = new RiskServiceImpl(userRepo, betRepo, riskRepo); }

    @Test
    void assessRiskUserNotFound() {
        when(userRepo.findById(1L)).thenReturn(Optional.empty());
        try {
            service.assessRisk(1L);
            fail("expected exception");
        } catch (IllegalArgumentException e) {
            // ok
        }
    }
}
