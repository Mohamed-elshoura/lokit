package com.elshoura.lokit.models.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductVariantDetailsResponse (

        Long id,

        Long sizeId,
        String sizeName,

        Long colorId,
        String colorName,

        String sku,
        BigDecimal price,
        Integer stock

){

}
