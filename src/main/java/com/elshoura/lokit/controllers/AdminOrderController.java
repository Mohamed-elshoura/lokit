package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.UpdateOrderStatusRequest;
import com.elshoura.lokit.models.dto.response.OrderResponse;
import com.elshoura.lokit.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/orders")
@Tag(name = "Admin Orders")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class AdminOrderController {

    private final OrderService orderService;

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{orderId}/status")
    @Operation(summary = "Update order status - Admin only")
    public ResponseEntity<OrderResponse> updateOrderStatusApi(
            @PathVariable Long orderId,
            @Valid @RequestBody UpdateOrderStatusRequest request
    ) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, request));
    }
}
