package com.elshoura.lokit.models.dto.response;

import com.elshoura.lokit.models.entitys.OrderItem;
import com.elshoura.lokit.utils.enums.OrderStatus;
import com.elshoura.lokit.utils.enums.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderResponse(


    Long id,
    Long userId,
    String firstName,
    String lastName,
    String email,
    String phone,

    Long addressId,
    String street,
    String city,
    String governorate,
    String country,
    String zipCode,


    List<OrderItemResponse> items,

    OrderStatus status,
    PaymentMethod paymentMethod,

    BigDecimal subtotal,
    BigDecimal tax,
    BigDecimal shipping,
    BigDecimal totalPrice,

    String notes,

    LocalDateTime createdAt,
    LocalDateTime deliveredAt,
    LocalDateTime cancelledAt
) {
}
