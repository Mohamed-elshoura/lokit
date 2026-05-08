package com.elshoura.lokit.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MaterialRequest (

        @NotBlank
        @Size(min = 3, max = 50, message = "material name must be between 3 and 50 character")
        String name
){
}
