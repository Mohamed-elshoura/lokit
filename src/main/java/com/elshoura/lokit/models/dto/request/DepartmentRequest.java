package com.elshoura.lokit.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DepartmentRequest(

        @NotBlank
        @Size(min = 3, max = 100, message = "Department name must be between 3 and 100 character")
        String name
) {
}
