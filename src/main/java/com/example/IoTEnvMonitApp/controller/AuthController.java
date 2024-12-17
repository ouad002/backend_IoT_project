package com.example.IoTEnvMonitApp.controller;

import com.example.IoTEnvMonitApp.model.AuthRequest;
import com.example.IoTEnvMonitApp.model.AuthResponse;
import com.example.IoTEnvMonitApp.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/request")
    public AuthResponse requestAuth(@RequestBody AuthRequest authRequest) {
        return authService.authorize(authRequest);
    }
}


