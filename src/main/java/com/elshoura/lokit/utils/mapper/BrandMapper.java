package com.elshoura.lokit.utils.mapper;


import com.elshoura.lokit.models.dto.request.BrandRequest;
import com.elshoura.lokit.models.dto.response.BrandResponse;
import com.elshoura.lokit.models.entitys.Brand;

public class BrandMapper {

    private BrandMapper() {
        throw new IllegalStateException(" Utility class can't be instantiated");
    }

    public static Brand toBrand(BrandRequest request) {
      return  Brand.builder()
              .name(request.name().trim())
                .build();
    }
    public  static BrandResponse mapToResponse(Brand brand) {
      return   BrandResponse.builder()
              .id(brand.getId())
              .name(brand.getName())
              .build();
    }
}
