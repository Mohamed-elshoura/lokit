package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.request.ProductRequest;
import com.elshoura.lokit.models.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest productRequest);

    ProductResponse updateProduct(Long id, ProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    void deleteProduct(Long id);


}
