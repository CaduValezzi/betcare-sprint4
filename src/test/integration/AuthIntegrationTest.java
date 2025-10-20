package com.example.betcare.integration;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthIntegrationTest {
    @Autowired MockMvc mvc;

    @Test
    void registerAndLoginFlow() throws Exception {
        String json = "{"username":"test","password":"1234"}";
        mvc.perform(post("/api/auth/register").contentType("application/json").content(json))
                .andExpect(status().isOk());

        mvc.perform(post("/api/auth/login").contentType("application/json").content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }
}
