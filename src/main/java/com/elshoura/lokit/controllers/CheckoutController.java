package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.CheckoutRequest;
import com.elshoura.lokit.models.dto.response.OrderResponse;
import com.elshoura.lokit.security.CustomUserDetails;
import com.elshoura.lokit.service.CheckoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Checkout")
@SecurityRequirement(name = "bearerAuth")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping
    @Operation(summary = "Checkout current user cart")
    public ResponseEntity<OrderResponse> checkout(
            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody CheckoutRequest request
    ) {
        Long userId = userDetails.getUser().getId();

        OrderResponse response = checkoutService.checkout(userId, request);

        return ResponseEntity.ok(response);
    }
}
