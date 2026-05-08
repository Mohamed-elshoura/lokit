package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.request.LoginRequest;
import com.elshoura.lokit.models.dto.request.RegisterRequest;
import com.elshoura.lokit.models.dto.response.AuthResponse;

public interface AuthService {

    public AuthResponse register(RegisterRequest request) ;
    public AuthResponse login(LoginRequest request) ;

    }
