package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.MaterialRequest;
import com.elshoura.lokit.models.dto.response.MaterialResponse;
import com.elshoura.lokit.service.MaterialService;
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
@RequestMapping("/material")
@Tag(name = "Materials", description = "Material management APIs")
public class MaterialController {

    private final MaterialService materialService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create material - Admin only")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MaterialResponse> addMaterialApi(@Valid @RequestBody MaterialRequest materialRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body( materialService.addMaterial(materialRequest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update material - Admin only")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MaterialResponse> updateMaterialApi(@PathVariable Long id, @Valid @RequestBody MaterialRequest materialRequest) {

        return ResponseEntity.status(HttpStatus.OK).body( materialService.updateMaterial(id,materialRequest));
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get material by ID")
    public ResponseEntity<MaterialResponse> getMaterialByIdApi(@PathVariable Long id) {

        return ResponseEntity.ok().body( materialService.getMaterialById(id));

    }

    @GetMapping
    @Operation(summary = "Get all materials")
    public ResponseEntity<List<MaterialResponse>> getAllMaterialApi() {

        return ResponseEntity.ok().body(materialService.getAllMaterials());

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete material - Admin only")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deleteMaterialApi(@PathVariable Long id) {

        materialService.deleteMaterial(id);

        return ResponseEntity.noContent().build();

    }

}
