package com.elshoura.lokit.utils.mapper;

import com.elshoura.lokit.models.dto.request.DepartmentRequest;
import com.elshoura.lokit.models.dto.response.DepartmentResponse;
import com.elshoura.lokit.models.entitys.Department;

public class DepartmentMapper {

    private DepartmentMapper() {
        throw new IllegalStateException(" Utility class can't be instantiated");
    }

    public static Department toDepartment(DepartmentRequest departmentRequest) {

        return Department.builder()
                .name(departmentRequest.name().trim())
                .build();
    }

    public static DepartmentResponse toDepartmentResponse(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }
}
