package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.DepartmentRequest;
import com.elshoura.lokit.models.dto.response.DepartmentResponse;
import com.elshoura.lokit.service.DepartmentService;
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
@RequestMapping("/department")
@Tag(name = "Departments", description = "Department management APIs")
public class DepartmentController {


    private final DepartmentService departmentService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create department - Admin only")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<DepartmentResponse> addDepartmentApi(@Valid @RequestBody DepartmentRequest departmentRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body( departmentService.addDepartment(departmentRequest));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update department - Admin only")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<DepartmentResponse> updateDepartmentApi(@PathVariable Long id, @Valid @RequestBody DepartmentRequest departmentRequest) {

        return ResponseEntity.status(HttpStatus.OK).body( departmentService.updateDepartment(id,departmentRequest));
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get department by ID")
    public ResponseEntity<DepartmentResponse> getDepartmentByIdApi(@PathVariable Long id) {

        return ResponseEntity.ok().body( departmentService.getDepartmentById(id));

    }

    @GetMapping
    @Operation(summary = "Get all departments")
    public ResponseEntity<List<DepartmentResponse>> getAllDepartmentApi() {

        return ResponseEntity.ok().body(departmentService.getAllDepartments());

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete department - Admin only")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deleteDepartmentApi(@PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();

    }
}
