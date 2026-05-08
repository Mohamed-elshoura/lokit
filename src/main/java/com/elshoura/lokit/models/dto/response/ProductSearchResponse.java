package com.elshoura.lokit.models.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record ProductSearchResponse(

        Long id,
        String name,
        String description,
        String brandName,
        String categoryName,
        String imageUrl,
        BigDecimal minPrice
) {
}
