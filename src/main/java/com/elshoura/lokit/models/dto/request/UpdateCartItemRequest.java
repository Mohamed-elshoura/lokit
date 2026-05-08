package com.elshoura.lokit.models.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateCartItemRequest(

        @NotNull
        @Min(value = 1,message = "The Quantity Less than ")
        Integer quantity
) {
}