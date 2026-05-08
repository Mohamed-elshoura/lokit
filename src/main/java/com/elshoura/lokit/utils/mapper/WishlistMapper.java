package com.elshoura.lokit.utils.mapper;

import com.elshoura.lokit.models.dto.request.WishlistRequest;
import com.elshoura.lokit.models.dto.response.WishlistResponse;
import com.elshoura.lokit.models.entitys.Product;
import com.elshoura.lokit.models.entitys.User;
import com.elshoura.lokit.models.entitys.WishlistItem;

public class WishlistMapper {

    private WishlistMapper(){
        throw new IllegalStateException("Utility class can't be instantiated");
    }

    public static WishlistItem toWishlist( User user , Product product){

       return WishlistItem.builder()
                .user(user)
                .product(product)
                .build();

    }
    public static WishlistResponse toWishlistItemResponse(WishlistItem item){

        return WishlistResponse.builder()
                .id(item.getId())
                .productId(item.getProduct().getId())
                .productName(item.getProduct().getName())
                .brandName(item.getProduct().getBrand().getName())
                .categoryName(item.getProduct().getCategory().getName())
                .createdAt(item.getCreatedAt())
                .build();
    }

}
