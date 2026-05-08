package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.BrandRequest;
import com.elshoura.lokit.models.dto.response.BrandResponse;
import com.elshoura.lokit.service.BrandService;
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
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BrandResponse> addBrandApi(@Valid @RequestBody BrandRequest brandRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body( brandService.addBrand(brandRequest));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BrandResponse> updateBrandApi(@PathVariable Long id, @Valid @RequestBody BrandRequest brandRequest) {

        return ResponseEntity.status(HttpStatus.OK).body( brandService.updateBrand(id,brandRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getBrandByIdApi(@PathVariable Long id) {

        return ResponseEntity.ok().body( brandService.getBrandById(id));

    }

    @GetMapping
    public ResponseEntity<List<BrandResponse>> getAllBrands() {

        return ResponseEntity.ok().body(brandService.getAllBrands());

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBrandApi(@PathVariable Long id) {

        brandService.deleteBrandById(id);

       return ResponseEntity.noContent().build();

    }
}
