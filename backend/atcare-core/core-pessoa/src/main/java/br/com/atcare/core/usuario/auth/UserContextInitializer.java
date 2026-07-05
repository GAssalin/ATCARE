package br.com.atcare.core.usuario.auth;

import jakarta.servlet.http.HttpServletRequest;

public interface UserContextInitializer {
    void initialize(HttpServletRequest request);
}