package com.elshoura.lokit.utils.mapper;

import com.elshoura.lokit.models.entitys.Cart;
import com.elshoura.lokit.models.entitys.User;

public class CartMapper {

    private CartMapper() {
        throw new IllegalStateException(" Utility class can't be instantiated");
    }

    public static Cart toCart(User user) {
        return Cart.builder()
                .user(user)
                .build();
    }
}
