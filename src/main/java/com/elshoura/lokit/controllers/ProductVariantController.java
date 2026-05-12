package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.ProductVariantRequest;
import com.elshoura.lokit.models.dto.request.UpdateStockRequest;
import com.elshoura.lokit.models.dto.response.ProductVariantResponse;
import com.elshoura.lokit.service.ProductVariantService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/variants")
@Tag(name = "Product Variants")
public class ProductVariantController {


    private final ProductVariantService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add product variant - Admin only")
    public ResponseEntity<ProductVariantResponse> addVariantApi(@Valid @RequestBody ProductVariantRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addVariant(request));
    }

    @GetMapping
    @Operation(summary = "Get all product variants")
    public ResponseEntity<List<ProductVariantResponse>> getAllVariantApi() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product variant by ID")
    public ResponseEntity<ProductVariantResponse> getVariantByIdApi(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Get product variants by product")
    public ResponseEntity<List<ProductVariantResponse>> getByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(service.getByProduct(productId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update product variant - Admin only")
    public ResponseEntity<ProductVariantResponse> update(@PathVariable Long id,
                                                          @Valid @RequestBody ProductVariantRequest request) {
        return ResponseEntity.ok(service.updateVariant(id, request));
    }

    @PatchMapping("/{id}/stock")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update variant stock - Admin only")
    public ResponseEntity<ProductVariantResponse> updateStock(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStockRequest request
    ) {
        return ResponseEntity.ok(service.updateStock(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete product variant - Admin only")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
