package com.elshoura.lokit.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressRequest {


    @NotBlank
    @Size(max = 100)
    private String country;

    @NotBlank
    @Size(max = 100)
    private String city;

    @NotBlank
    @Size(max = 255)
    private String street;

    @Size(max = 20)
    private String zipCode;

    @Size(max = 100)
    private String governorate;
}
