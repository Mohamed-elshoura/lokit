package com.elshoura.lokit.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank
        @Size(min = 3, max = 100, message = "category name must be between 3 and 100 character")
        String name
) {
}
