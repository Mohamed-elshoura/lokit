package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.response.ProductSearchResponse;

import java.util.List;

public interface HomeProductService {


    List<ProductSearchResponse> getNewArrivals();

    List<ProductSearchResponse> getLatestProducts();

    List<ProductSearchResponse> getProductsByDepartment(Long departmentId);
}
