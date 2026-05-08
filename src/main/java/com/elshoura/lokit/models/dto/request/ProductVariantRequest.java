package com.elshoura.lokit.models.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductVariantRequest(
        @NotNull(message = "Product ID is required")
        Long productId,

        @NotNull(message = "Size ID is required")
        Long sizeId,

        @NotNull(message = "Color ID is required")
        Long colorId,

        @NotNull(message = "sku is required")
        String sku,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        @Digits(integer = 10, fraction = 2, message = "Price format is invalid")
        BigDecimal price,

        @NotNull(message = "Stock is required")
        @Min(value = 0, message = "Stock cannot be negative")
        Integer stock

) {
}
