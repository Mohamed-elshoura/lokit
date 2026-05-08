package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.ChangePasswordRequest;
import com.elshoura.lokit.models.dto.request.UpdateAccountRequest;
import com.elshoura.lokit.models.dto.response.AccountResponse;
import com.elshoura.lokit.security.CustomUserDetails;
import com.elshoura.lokit.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<AccountResponse> getMyAccountApi(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.ok(accountService.getMyAccount(userId));
    }

    @PutMapping
    public ResponseEntity<AccountResponse> updateMyAccountApi(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody UpdateAccountRequest request
    ) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.ok(accountService.updateMyAccount(userId, request));
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> changePasswordApi(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody ChangePasswordRequest request
    ) {
        Long userId = userDetails.getUser().getId();

        accountService.changePassword(userId, request);

        return ResponseEntity.noContent().build();
    }
}
