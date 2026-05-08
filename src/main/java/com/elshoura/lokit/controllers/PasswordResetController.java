package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.ForgotPasswordRequest;
import com.elshoura.lokit.models.dto.request.ResetPasswordRequest;
import com.elshoura.lokit.models.dto.request.VerifyResetCodeRequest;
import com.elshoura.lokit.models.dto.response.SimpleMessageResponse;
import com.elshoura.lokit.service.PasswordResetService;
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
public class PasswordResetController {


    private final PasswordResetService passwordResetService;

    @PostMapping("/forgot-password")
    public ResponseEntity<SimpleMessageResponse> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request
    ) {
        return ResponseEntity.ok(passwordResetService.forgotPassword(request));
    }

    @PostMapping("/verify-reset-code")
    public ResponseEntity<SimpleMessageResponse> verifyResetCode(
            @Valid @RequestBody VerifyResetCodeRequest request
    ) {
        return ResponseEntity.ok(passwordResetService.verifyResetCode(request));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<SimpleMessageResponse> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request
    ) {
        return ResponseEntity.ok(passwordResetService.resetPassword(request));
    }
}
