package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.ProductImageRequest;
import com.elshoura.lokit.models.dto.response.ProductImageResponse;
import com.elshoura.lokit.service.ProductImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-images")
public class ProductImageController {

    private final ProductImageService productImageService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductImageResponse> addProductImageApi(@Valid @RequestBody ProductImageRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productImageService.addProductImage(request));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductImageResponse>> getByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productImageService.getByProduct(productId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductImageResponse> update(@PathVariable Long id,
                                                       @Valid @RequestBody ProductImageRequest request) {
        return ResponseEntity.ok(productImageService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productImageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/main")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductImageResponse> setMainImage(@PathVariable Long id) {
        return ResponseEntity.ok(productImageService.setMainImage(id));
    }

}
