package com.elshoura.lokit.service;

import com.elshoura.lokit.models.entitys.Cart;

public interface CartService {

    Cart getOrCreateCart(Long userId);
}
