package com.elshoura.lokit.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductRequest(


        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must be at most 100 characters")
        String name,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Brand is required")
        @Positive(message = "Brand ID must be greater than 0")
        Long brandId,

        @NotNull(message = "Category is required")
        @Positive(message = "Category ID must be greater than 0")
        Long categoryId,

        @NotNull(message = "Material is required")
        @Positive(message = "Material ID must be greater than 0")
        Long materialId,

        @NotNull(message = "Department is required")
        @Positive(message = "Department ID must be greater than 0")
        Long departmentId

) {
}
