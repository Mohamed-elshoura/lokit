package com.elshoura.lokit.service;

import com.elshoura.lokit.errors.exception.AlreadyExistException;
import com.elshoura.lokit.errors.exception.NotFoundException;
import com.elshoura.lokit.models.dto.request.MaterialRequest;
import com.elshoura.lokit.models.dto.response.MaterialResponse;
import com.elshoura.lokit.models.entitys.Material;
import com.elshoura.lokit.repository.MaterialRepository;
import com.elshoura.lokit.utils.mapper.MaterialMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.elshoura.lokit.utils.mapper.MaterialMapper.toMaterial;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    @Override
     public MaterialResponse addMaterial(MaterialRequest materialRequest){

         String name = materialRequest.name().trim();

         materialRepository.findByNameIgnoreCase(name)
                 .ifPresent(d -> {throw new AlreadyExistException("material  already exists");});

         Material material = toMaterial(materialRequest);

         Material savedMaterial = materialRepository.save(material);

         return MaterialMapper.toMaterialResponse(savedMaterial);
     }
     @Override
    public MaterialResponse updateMaterial(Long id,MaterialRequest materialRequest){

         Material material= materialRepository.findById(id).orElseThrow(() -> new NotFoundException("material not found"));

         String name = materialRequest.name().trim();

         materialRepository.findByNameIgnoreCase(name)
                 .filter(d -> material.getId().equals(id))
                 .ifPresent(d -> {throw new AlreadyExistException("material already exists");
                 });

         material.setName(materialRequest.name());
         Material savedMaterial = materialRepository.save(material);

         return MaterialMapper.toMaterialResponse(savedMaterial);

    }
    @Override
   public MaterialResponse getMaterialById(Long id){


       Material material= materialRepository.findById(id)
               .orElseThrow(() -> new NotFoundException("material not found"));

       return MaterialMapper.toMaterialResponse(material);

   }
   @Override
    public List<MaterialResponse> getAllMaterials(){


        return materialRepository.findAllByOrderByIdAsc().stream()
                .map(MaterialMapper::toMaterialResponse)
                .toList();
    }
    @Override
    public void deleteMaterial(Long id){

        materialRepository.findById(id).orElseThrow(()->new NotFoundException("material not found"));

        materialRepository.deleteById(id);
    }
}
