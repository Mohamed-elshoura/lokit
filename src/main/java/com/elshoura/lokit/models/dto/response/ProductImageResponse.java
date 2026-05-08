package com.elshoura.lokit.models.dto.response;

import lombok.Builder;

@Builder
public record ProductImageResponse(

         Long id,
         Long productId,
         String productName,
         String imageUrl,
         Boolean isMain

) {
}
