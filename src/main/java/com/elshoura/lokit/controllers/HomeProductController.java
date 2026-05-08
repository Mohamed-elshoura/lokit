package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.response.ProductSearchResponse;
import com.elshoura.lokit.service.HomeProductService;
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
public class HomeProductController {

    private final HomeProductService homeProductService;

    @GetMapping("/new-arrivals")
    public ResponseEntity<List<ProductSearchResponse>> getNewArrivals() {
        return ResponseEntity.ok(homeProductService.getNewArrivals());
    }

    @GetMapping("/latest")
    public ResponseEntity<List<ProductSearchResponse>> getLatestProducts() {
        return ResponseEntity.ok(homeProductService.getLatestProducts());
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<ProductSearchResponse>> getProductsByDepartment(
            @PathVariable Long departmentId
    ) {
        return ResponseEntity.ok(homeProductService.getProductsByDepartment(departmentId));
    }

}
