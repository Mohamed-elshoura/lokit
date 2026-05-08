package com.elshoura.lokit.models.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {

    private Long id;
    private String country;
    private String city;
    private String street;
    private String zipCode;
    private String governorate;
}
