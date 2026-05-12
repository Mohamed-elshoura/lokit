package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.ProductImageRequest;
import com.elshoura.lokit.models.dto.response.ProductImageResponse;
import com.elshoura.lokit.service.ProductImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Product Images")
@SecurityRequirement(name = "bearerAuth")
public class ProductImageController {

    private final ProductImageService productImageService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add product image - Admin only")
    public ResponseEntity<ProductImageResponse> addProductImageApi(@Valid @RequestBody ProductImageRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productImageService.addProductImage(request));
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Add product image - Admin only")
    public ResponseEntity<List<ProductImageResponse>> getByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productImageService.getByProduct(productId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update product image - Admin only")
    public ResponseEntity<ProductImageResponse> update(@PathVariable Long id,
                                                       @Valid @RequestBody ProductImageRequest request) {
        return ResponseEntity.ok(productImageService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete product image - Admin only")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productImageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/main")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Set main product image - Admin only")
    public ResponseEntity<ProductImageResponse> setMainImage(@PathVariable Long id) {
        return ResponseEntity.ok(productImageService.setMainImage(id));
    }

}
