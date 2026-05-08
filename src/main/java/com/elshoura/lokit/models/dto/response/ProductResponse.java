package com.elshoura.lokit.models.dto.response;

import lombok.Builder;

@Builder
public record ProductResponse(
    Long id,
    String name,
    String description,
    String brandName,
    String categoryName,
    String materialName,
    String departmentName,
    Boolean active
){
}
