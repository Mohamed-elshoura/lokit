package com.elshoura.lokit.repository;

import com.elshoura.lokit.models.entitys.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {

    Optional<PasswordResetToken> findTopByUserIdAndCodeAndUsedFalseOrderByCreatedAtDesc(
            Long userId,
            String code
    );
}
