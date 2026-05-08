package com.elshoura.lokit.utils.mapper;

import com.elshoura.lokit.models.dto.request.DepartmentRequest;
import com.elshoura.lokit.models.dto.request.MaterialRequest;
import com.elshoura.lokit.models.dto.response.DepartmentResponse;
import com.elshoura.lokit.models.dto.response.MaterialResponse;
import com.elshoura.lokit.models.entitys.Department;
import com.elshoura.lokit.models.entitys.Material;

public class MaterialMapper {


    private MaterialMapper() {
        throw new IllegalStateException(" Utility class can't be instantiated");
    }

    public static Material toMaterial(MaterialRequest materialRequest) {

        return Material.builder()
                .name(materialRequest.name().trim())
                .build();
    }

    public static MaterialResponse toMaterialResponse(Material material) {
        return MaterialResponse.builder()
                .id(material.getId())
                .name(material.getName())
                .build();
    }
}
