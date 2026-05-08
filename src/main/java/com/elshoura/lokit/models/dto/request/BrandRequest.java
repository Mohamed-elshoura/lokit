package com.elshoura.lokit.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BrandRequest(
        @NotBlank(message = "Brand Name is Required")
        @Size(max = 80, message = "Brand name must not exceed 80 characters")
        String name
) {
}