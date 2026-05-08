package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.CheckoutRequest;
import com.elshoura.lokit.models.dto.response.OrderResponse;
import com.elshoura.lokit.security.CustomUserDetails;
import com.elshoura.lokit.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping
    public ResponseEntity<OrderResponse> checkout(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody CheckoutRequest request
    ) {
        Long userId = userDetails.getUser().getId();

        OrderResponse response = checkoutService.checkout(userId, request);

        return ResponseEntity.ok(response);
    }
}
