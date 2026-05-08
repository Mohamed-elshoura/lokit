package com.elshoura.lokit.models.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
public record ProductVariantResponse(

         Long id,
         String productName,
         String sizeName,
         String colorName,
         String sku,
         BigDecimal price,
         Integer stock
) {
}
