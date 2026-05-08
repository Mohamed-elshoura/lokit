package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.request.UpdateOrderStatusRequest;
import com.elshoura.lokit.models.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getMyOrders(Long userId);

    OrderResponse getMyOrderById(Long userId,Long orderId);

    OrderResponse updateOrderStatus(Long orderId, UpdateOrderStatusRequest request);

    OrderResponse cancelMyOrder(Long userId, Long orderId);


}
