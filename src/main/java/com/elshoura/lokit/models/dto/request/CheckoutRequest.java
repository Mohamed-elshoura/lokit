package com.elshoura.lokit.models.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CheckoutRequest {

    @NotNull(message = "Address id is required")
    private Long addressId;

    private String notes;
}
