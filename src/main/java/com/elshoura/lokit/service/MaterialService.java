package com.elshoura.lokit.service;

import com.elshoura.lokit.models.dto.request.MaterialRequest;
import com.elshoura.lokit.models.dto.response.MaterialResponse;

import java.util.List;

public interface MaterialService {


    MaterialResponse addMaterial(MaterialRequest materialRequest);

    MaterialResponse updateMaterial(Long id,MaterialRequest materialRequest);

    MaterialResponse getMaterialById(Long id);

    List<MaterialResponse> getAllMaterials();

    void deleteMaterial(Long id);
}
