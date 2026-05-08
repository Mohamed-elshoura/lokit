package com.elshoura.lokit.models.dto.response;

import lombok.Builder;

@Builder
public record AccountResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone


) {
}
