package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.response.ProductSearchResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProductSearchService {

    List<ProductSearchResponse> searchProducts(
            String keyword,
            Long brandId,
            Long categoryId,
            Long colorId,
            Long sizeId,
            BigDecimal minPrice,
            BigDecimal maxPrice
    );
}
