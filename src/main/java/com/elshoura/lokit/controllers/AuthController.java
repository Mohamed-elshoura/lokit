package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.LoginRequest;
import com.elshoura.lokit.models.dto.request.RegisterRequest;
import com.elshoura.lokit.models.dto.response.AuthResponse;
import com.elshoura.lokit.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Register and login APIs")

public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register new user")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerApi(@Valid @RequestBody RegisterRequest registerRequest){


        return  ResponseEntity.ok().body(authService.register(registerRequest));
    }

    @Operation(summary = "Login user")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginApi(@Valid @RequestBody LoginRequest loginRequest){
        return  ResponseEntity.ok().body(authService.login(loginRequest));
    }

}
