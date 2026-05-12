package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.BrandRequest;
import com.elshoura.lokit.models.dto.response.BrandResponse;
import com.elshoura.lokit.service.BrandService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/brand")
@Tag(name = "Brands", description = "Brand management APIs")
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create brand - Admin only")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<BrandResponse> addBrandApi(@Valid @RequestBody BrandRequest brandRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body( brandService.addBrand(brandRequest));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update brand - Admin only")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<BrandResponse> updateBrandApi(@PathVariable Long id, @Valid @RequestBody BrandRequest brandRequest) {

        return ResponseEntity.status(HttpStatus.OK).body( brandService.updateBrand(id,brandRequest));
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get brand by ID")
    public ResponseEntity<BrandResponse> getBrandByIdApi(@PathVariable Long id) {

        return ResponseEntity.ok().body( brandService.getBrandById(id));

    }

    @GetMapping
    @Operation(summary = "Get all brands")
    public ResponseEntity<List<BrandResponse>> getAllBrands() {

        return ResponseEntity.ok().body(brandService.getAllBrands());

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete brand - Admin only")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deleteBrandApi(@PathVariable Long id) {

        brandService.deleteBrandById(id);

       return ResponseEntity.noContent().build();

    }
}
