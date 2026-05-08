package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.request.CartItemRequest;
import com.elshoura.lokit.models.dto.request.UpdateCartItemRequest;
import com.elshoura.lokit.models.dto.response.CartResponse;

public interface CartItemService {

    CartResponse getMyCart(Long userId);

    CartResponse addItem(Long userId, CartItemRequest cartItemRequest);

    public CartResponse updateItem(Long userId, Long itemId, UpdateCartItemRequest request);

    CartResponse removeItem(Long userId, Long itemId);

}
