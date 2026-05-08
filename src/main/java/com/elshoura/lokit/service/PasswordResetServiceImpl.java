package com.elshoura.lokit.service;

import com.elshoura.lokit.errors.exception.InvalidPasswordException;
import com.elshoura.lokit.errors.exception.NotFoundException;
import com.elshoura.lokit.errors.exception.VerificationException;
import com.elshoura.lokit.models.dto.request.ForgotPasswordRequest;
import com.elshoura.lokit.models.dto.request.ResetPasswordRequest;
import com.elshoura.lokit.models.dto.request.VerifyResetCodeRequest;
import com.elshoura.lokit.models.dto.response.SimpleMessageResponse;
import com.elshoura.lokit.models.entitys.PasswordResetToken;
import com.elshoura.lokit.models.entitys.User;
import com.elshoura.lokit.repository.PasswordResetTokenRepository;
import com.elshoura.lokit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {


    private static final int CODE_EXPIRATION_MINUTES = 10;

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    public SimpleMessageResponse forgotPassword(ForgotPasswordRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new NotFoundException("User not found"));

        String code = generateCode();

        PasswordResetToken resetToken = PasswordResetToken.builder()
                .user(user)
                .code(code)
                .expiresAt(LocalDateTime.now().plusMinutes(CODE_EXPIRATION_MINUTES))
                .used(false)
                .build();

        passwordResetTokenRepository.save(resetToken);

        emailService.sendPasswordResetCode(user.getEmail(), code);

        return SimpleMessageResponse.builder()
                .message("Verification code sent successfully")
                .build();
    }

    @Override
    public SimpleMessageResponse verifyResetCode(VerifyResetCodeRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new NotFoundException("User not found"));

        getValidResetToken(user.getId(), request.code());

        return SimpleMessageResponse.builder()
                .message("Code verified successfully")
                .build();
    }

    @Override
    public SimpleMessageResponse resetPassword(ResetPasswordRequest request) {

        if (!request.newPassword().equals(request.confirmNewPassword())) {
            throw new InvalidPasswordException("New password and confirm password do not match");
        }

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new NotFoundException("User not found"));

        PasswordResetToken resetToken = getValidResetToken(user.getId(), request.code());

        if (passwordEncoder.matches(request.newPassword(), user.getPassword())) {
            throw new InvalidPasswordException("New password must be different from old password");
        }

        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);

        resetToken.setUsed(true);
        passwordResetTokenRepository.save(resetToken);

        return SimpleMessageResponse.builder()
                .message("Password reset successfully")
                .build();
    }

    private PasswordResetToken getValidResetToken(Long userId, String code) {

        PasswordResetToken token = passwordResetTokenRepository
                .findTopByUserIdAndCodeAndUsedFalseOrderByCreatedAtDesc(userId, code)
                .orElseThrow(() -> new VerificationException("Invalid verification code"));

        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new VerificationException("Verification code expired");
        }

        return token;
    }

    private String generateCode() {
        SecureRandom random = new SecureRandom();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }
}
