package com.elshoura.lokit.models.dto.response;

import lombok.Builder;

@Builder
public record SimpleMessageResponse(
        String message
) {
}
