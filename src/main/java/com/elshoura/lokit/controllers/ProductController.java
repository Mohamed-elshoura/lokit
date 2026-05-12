package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.ProductRequest;
import com.elshoura.lokit.models.dto.response.ProductDetailsResponse;
import com.elshoura.lokit.models.dto.response.ProductResponse;
import com.elshoura.lokit.service.ProductDetailsService;
import com.elshoura.lokit.service.ProductService;
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
@RequestMapping("/product")
@Tag(name = "Products")
public class ProductController {

    private final ProductService productService;
    private final ProductDetailsService productDetailsService;

    @PostMapping
    @Operation(summary = "Create product - Admin only")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
public ResponseEntity<ProductResponse> addProductApi(@Valid @RequestBody ProductRequest productRequest){

        return  new ResponseEntity<>(productService.addProduct(productRequest), HttpStatus.CREATED);
    }

        @GetMapping
        @Operation(summary = "Get all products")
        public ResponseEntity<List<ProductResponse>> getAllProductsApi() {
            return ResponseEntity.ok(productService.getAllProducts());
        }

        @GetMapping("/{id}")
        @Operation(summary = "Get product by ID")
        public ResponseEntity<ProductResponse> getProductByIdApi(@PathVariable Long id) {
            return ResponseEntity.ok(productService.getProductById(id));
        }

    @GetMapping("/{id}/details")
    @Operation(summary = "Get product details")
    public ResponseEntity<ProductDetailsResponse> getProductDetailsApi(@PathVariable Long id) {
        return ResponseEntity.ok(productDetailsService.getProductDetails(id));
    }

        @PutMapping("/{id}")
        @PreAuthorize("hasRole('ADMIN')")
        @Operation(summary = "Update product - Admin only")
        public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
                                                             @Valid @RequestBody ProductRequest productRequest) {
            return ResponseEntity.ok(productService.updateProduct(id, productRequest));
        }

        @DeleteMapping("/{id}")
        @PreAuthorize("hasRole('ADMIN')")
        @Operation(summary = "Delete product - Admin only")
        public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }

}
