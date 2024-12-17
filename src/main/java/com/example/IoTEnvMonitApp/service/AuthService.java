package com.example.IoTEnvMonitApp.service;

import com.example.IoTEnvMonitApp.model.AuthRequest;
import com.example.IoTEnvMonitApp.model.AuthResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public AuthResponse authorize(AuthRequest authRequest) {
        // Simple hardcoded authentication logic for demonstration
        return new AuthResponse(authRequest.getRoom() == 2); // Authorized if room == 2
    }
}

