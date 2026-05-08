package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.request.ProductImageRequest;
import com.elshoura.lokit.models.dto.response.ProductImageResponse;

import java.util.List;

public interface ProductImageService {

    ProductImageResponse addProductImage(ProductImageRequest request);

    List<ProductImageResponse> getByProduct(Long productId);

    ProductImageResponse update(Long id, ProductImageRequest request);

    void delete(Long id);

    ProductImageResponse setMainImage(Long imageId);


}
