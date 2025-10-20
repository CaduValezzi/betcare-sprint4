package com.example.betcare.unit;

import com.example.betcare.repository.UserRepository;
import com.example.betcare.security.JwtTokenProvider;
import com.example.betcare.service.impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    @Mock UserRepository repo;
    @Mock PasswordEncoder encoder;
    @Mock JwtTokenProvider tokenProvider;
    UserServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new UserServiceImpl(repo, encoder, tokenProvider);
    }

    @Test
    void registerShouldHashPasswordAndSave() {
        when(repo.existsByUsername("u")).thenReturn(false);
        when(encoder.encode("p")).thenReturn("hashed");
        when(repo.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        var user = service.register("u", "p");
        assertEquals("u", user.getUsername());
        assertEquals("hashed", user.getPassword());
        verify(repo, times(1)).save(any());
    }
}
