package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.response.OrderResponse;
import com.elshoura.lokit.security.CustomUserDetails;
import com.elshoura.lokit.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Orders")
@SecurityRequirement(name = "bearerAuth")
public class OrderController {


    private final OrderService orderService;


    @GetMapping
    @Operation(summary = "Get current user orders")
    public ResponseEntity<List<OrderResponse>> getMyOrders(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.ok(orderService.getMyOrders(userId));
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Get current user order by ID")
    public ResponseEntity<OrderResponse> getMyOrderById(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long orderId
    ) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.ok(orderService.getMyOrderById(userId, orderId));
    }
    @PatchMapping("/{orderId}/cancel")
    @Operation(summary = "Cancel current user order")
    public ResponseEntity<OrderResponse> cancelMyOrder(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long orderId
    ) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.ok(orderService.cancelMyOrder(userId, orderId));
    }

}
