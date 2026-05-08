package com.elshoura.lokit.models.dto.response;

import lombok.Builder;

@Builder
public record MaterialResponse(

        Long id,
        String name
) {
}
