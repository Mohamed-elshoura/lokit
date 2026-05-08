package com.elshoura.lokit.models.dto.request;

import com.elshoura.lokit.utils.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderStatusRequest {
    @NotNull(message = "Order status is required")
    private OrderStatus status;
}
