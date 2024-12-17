package com.example.IoTEnvMonitApp.model;

public class AuthResponse {
    private boolean auth;

    public AuthResponse(boolean auth) {
        this.auth = auth;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    // Getters and Setters
}

