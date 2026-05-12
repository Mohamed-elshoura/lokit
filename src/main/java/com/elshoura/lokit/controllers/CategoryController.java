package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.BrandRequest;
import com.elshoura.lokit.models.dto.request.CategoryRequest;
import com.elshoura.lokit.models.dto.response.BrandResponse;
import com.elshoura.lokit.models.dto.response.CategoryResponse;
import com.elshoura.lokit.service.CategoryService;
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
@RequestMapping("/category")
@Tag(name = "Categories", description = "Category management APIs")
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create category - Admin only")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<CategoryResponse> addCategoryApi(@Valid @RequestBody CategoryRequest categoryRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body( categoryService.addCategory(categoryRequest));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update category - Admin only")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<CategoryResponse> updateCategoryApi(@PathVariable Long id, @Valid @RequestBody CategoryRequest categoryRequest) {

        return ResponseEntity.status(HttpStatus.OK).body( categoryService.updateCategory(id,categoryRequest));
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID")
    public ResponseEntity<CategoryResponse> getCategoryByIdApi(@PathVariable Long id) {

        return ResponseEntity.ok().body( categoryService.getCategory(id));

    }

    @GetMapping
    @Operation(summary = "Get all categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategoryApi() {

        return ResponseEntity.ok().body(categoryService.getAllCategories());

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete category - Admin only")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deleteCategoryApi(@PathVariable Long id) {

        categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();

    }
}
