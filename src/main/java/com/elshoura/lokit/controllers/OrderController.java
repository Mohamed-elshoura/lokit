package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.response.OrderResponse;
import com.elshoura.lokit.security.CustomUserDetails;
import com.elshoura.lokit.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;


    @GetMapping
    public ResponseEntity<List<OrderResponse>> getMyOrders(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.ok(orderService.getMyOrders(userId));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getMyOrderById(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long orderId
    ) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.ok(orderService.getMyOrderById(userId, orderId));
    }
    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<OrderResponse> cancelMyOrder(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long orderId
    ) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.ok(orderService.cancelMyOrder(userId, orderId));
    }

}
