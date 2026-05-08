package com.elshoura.lokit.models.dto.request;


import jakarta.validation.constraints.NotNull;

public record WishlistRequest(

        @NotNull(message = "Product id is required")
        Long productId
){
}
