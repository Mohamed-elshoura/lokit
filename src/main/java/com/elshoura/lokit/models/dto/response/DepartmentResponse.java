package com.elshoura.lokit.models.dto.response;

import lombok.Builder;

@Builder
public record DepartmentResponse (

        Long id,
        String name
) {
}
