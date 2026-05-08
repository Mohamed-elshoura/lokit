package com.elshoura.lokit.models.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record WishlistResponse(
         Long id,
         Long productId,
         String productName,
         String brandName,
         String categoryName,
         LocalDateTime createdAt

) {
}
