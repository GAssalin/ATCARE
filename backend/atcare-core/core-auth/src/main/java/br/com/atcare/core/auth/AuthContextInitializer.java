package br.com.atcare.core.auth;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthContextInitializer {
    void initialize(HttpServletRequest request);
}