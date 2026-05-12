package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.response.ProductSearchResponse;
import com.elshoura.lokit.service.HomeProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Home Products", description = "Home page product sections APIs")
public class HomeProductController {

    private final HomeProductService homeProductService;

    @GetMapping("/new-arrivals")
    @Operation(summary = "Get new arrival products")
    public ResponseEntity<List<ProductSearchResponse>> getNewArrivals() {
        return ResponseEntity.ok(homeProductService.getNewArrivals());
    }

    @GetMapping("/latest")
    @Operation(summary = "Get latest products")
    public ResponseEntity<List<ProductSearchResponse>> getLatestProducts() {
        return ResponseEntity.ok(homeProductService.getLatestProducts());
    }

    @GetMapping("/department/{departmentId}")
    @Operation(summary = "Get products by department")
    public ResponseEntity<List<ProductSearchResponse>> getProductsByDepartment(
            @PathVariable Long departmentId
    ) {
        return ResponseEntity.ok(homeProductService.getProductsByDepartment(departmentId));
    }

}
