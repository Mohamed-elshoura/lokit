package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.response.ProductSearchResponse;
import com.elshoura.lokit.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    @GetMapping("/search")
    public ResponseEntity<List<ProductSearchResponse>> searchProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long colorId,
            @RequestParam(required = false) Long sizeId,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice
    ) {
        return ResponseEntity.ok(
                productSearchService.searchProducts(
                        keyword,
                        brandId,
                        categoryId,
                        colorId,
                        sizeId,
                        minPrice,
                        maxPrice
                )
        );
    }
}
