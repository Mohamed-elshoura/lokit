package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.response.ProductDetailsResponse;

public interface ProductDetailsService {

    ProductDetailsResponse getProductDetails(Long productId);

}
