package com.elshoura.lokit.controllers;

import com.elshoura.lokit.models.dto.request.DepartmentRequest;
import com.elshoura.lokit.models.dto.response.DepartmentResponse;
import com.elshoura.lokit.service.DepartmentService;
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
public class DepartmentController {


    private final DepartmentService departmentService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DepartmentResponse> addDepartmentApi(@Valid @RequestBody DepartmentRequest departmentRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body( departmentService.addDepartment(departmentRequest));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DepartmentResponse> updateDepartmentApi(@PathVariable Long id, @Valid @RequestBody DepartmentRequest departmentRequest) {

        return ResponseEntity.status(HttpStatus.OK).body( departmentService.updateDepartment(id,departmentRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentByIdApi(@PathVariable Long id) {

        return ResponseEntity.ok().body( departmentService.getDepartmentById(id));

    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartmentApi() {

        return ResponseEntity.ok().body(departmentService.getAllDepartments());

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDepartmentApi(@PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();

    }
}
