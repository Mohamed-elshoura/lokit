package com.elshoura.lokit.models.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CartItemResponse (

 Long id,
 Long variantId,
 String productName,
 String sizeName,
 String colorName,
 Integer quantity,
 BigDecimal unitPrice,
 BigDecimal lineTotal

){
}
