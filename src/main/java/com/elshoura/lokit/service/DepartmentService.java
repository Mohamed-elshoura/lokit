package com.elshoura.lokit.service;


import com.elshoura.lokit.models.dto.request.DepartmentRequest;
import com.elshoura.lokit.models.dto.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse addDepartment(DepartmentRequest departmentRequest);

    DepartmentResponse updateDepartment(Long id,DepartmentRequest departmentRequest);

    DepartmentResponse getDepartmentById(Long id);

    List<DepartmentResponse> getAllDepartments();

    void deleteDepartment(Long id);

}
