package com.elshoura.lokit.models.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ProductDetailsResponse (

    Long id,
    String name,
    String description,

    Long brandId,
    String brandName,

    Long categoryId,
    String categoryName,

    Long departmentId,
    String departmentName,

    Long materialId,
    String materialName,

    Boolean active,

    List<ProductImageResponse> images,
    List<ProductVariantDetailsResponse> variants,

    LocalDateTime createdAt,
    LocalDateTime updatedAt
){
}
