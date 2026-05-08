package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.request.BrandRequest;
import com.elshoura.lokit.models.dto.response.BrandResponse;

import java.util.List;

public interface BrandService {

    BrandResponse addBrand(BrandRequest request);

    BrandResponse updateBrand(Long id,BrandRequest  request );

    BrandResponse getBrandById(Long id);

    List<BrandResponse> getAllBrands();

    void deleteBrandById(Long id);
}
