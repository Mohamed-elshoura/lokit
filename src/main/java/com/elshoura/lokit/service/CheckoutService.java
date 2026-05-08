package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.request.CheckoutRequest;
import com.elshoura.lokit.models.dto.response.OrderResponse;

public interface CheckoutService {

    OrderResponse checkout(Long userId, CheckoutRequest request);


}
