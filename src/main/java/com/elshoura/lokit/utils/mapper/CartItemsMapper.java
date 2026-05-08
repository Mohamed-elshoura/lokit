package com.elshoura.lokit.utils.mapper;

import com.elshoura.lokit.models.dto.response.CartItemResponse;
import com.elshoura.lokit.models.dto.response.CartResponse;
import com.elshoura.lokit.models.entitys.Cart;
import com.elshoura.lokit.models.entitys.CartItem;

import java.math.BigDecimal;
import java.util.List;

public class CartItemsMapper {

    private  CartItemsMapper() {

    throw new IllegalStateException("Utility class Cannot be instantiated");
    }

    /*public static CartResponse mapCartResponse(Cart cart) {

        List<CartItemResponse> items = m

    }*/



    public static CartItemResponse mapCartItem(CartItem item) {

        BigDecimal unitPrice = item.getVariant().getPrice();
        BigDecimal lineTotal = unitPrice.multiply(BigDecimal.valueOf(item.getQuantity()));

        return CartItemResponse.builder()
                .id(item.getId())
                .variantId(item.getVariant().getId())
                .productName(item.getVariant().getProduct().getName())
                .sizeName(item.getVariant().getSize().getName())
                .colorName(item.getVariant().getColor().getName())
                .quantity(item.getQuantity())
                .unitPrice(unitPrice)
                .lineTotal(lineTotal)
                .build();

    }

}
