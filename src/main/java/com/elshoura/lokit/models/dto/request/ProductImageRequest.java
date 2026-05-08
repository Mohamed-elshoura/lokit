package com.elshoura.lokit.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record ProductImageRequest(

        @NotNull(message = "Product ID is required")
        Long productId,

        @URL(message = "Invalid URL format")
        @NotBlank(message = "Image URL is required")
        String imageUrl,

        @NotNull(message = "isMain flag is required")
        Boolean isMain

) {

}
