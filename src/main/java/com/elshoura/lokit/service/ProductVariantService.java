package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.request.ProductVariantRequest;
import com.elshoura.lokit.models.dto.request.UpdateStockRequest;
import com.elshoura.lokit.models.dto.response.ProductVariantResponse;

import java.util.List;

public interface ProductVariantService {

    ProductVariantResponse addVariant(ProductVariantRequest request);


    List<ProductVariantResponse> getAll();

    ProductVariantResponse getById(Long id);

    List<ProductVariantResponse> getByProduct(Long productId);

    ProductVariantResponse updateVariant(Long id, ProductVariantRequest request);

    ProductVariantResponse updateStock(Long variantId, UpdateStockRequest request);

    void delete(Long id);
}
