package com.elshoura.lokit.models.dto.response;

import lombok.Builder;
import lombok.Data;


@Builder
public record BrandResponse(
        Long id ,
        String name
) {
}
