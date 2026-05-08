package com.elshoura.lokit.service;

import com.elshoura.lokit.errors.exception.AlreadyExistException;
import com.elshoura.lokit.errors.exception.NotFoundException;
import com.elshoura.lokit.models.dto.request.BrandRequest;
import com.elshoura.lokit.models.dto.response.BrandResponse;
import com.elshoura.lokit.models.entitys.Brand;
import com.elshoura.lokit.repository.BrandRepository;
import com.elshoura.lokit.utils.mapper.BrandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.elshoura.lokit.utils.mapper.BrandMapper.mapToResponse;
import static com.elshoura.lokit.utils.mapper.BrandMapper.toBrand;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public BrandResponse addBrand(BrandRequest request){

       String normalizedName = request.name().trim();

       brandRepository.findByNameIgnoreCase(normalizedName).ifPresent(brand -> {
           throw new AlreadyExistException("Brand already exists");
       });

       Brand brand = toBrand(request);

      Brand savedBrand= brandRepository.save(brand);

       return mapToResponse(savedBrand);
    }
    @Override
    public BrandResponse updateBrand(Long id,BrandRequest request){

       Brand brand = brandRepository.findById(id)
               .orElseThrow(()->new NotFoundException("Brand Not Found"));

    String normalizedName = request.name().trim();

       brandRepository.findByNameIgnoreCase(normalizedName)
               .filter(existingBrand -> !existingBrand.getId().equals(id))

               .ifPresent(B->{ throw new AlreadyExistException("Brand name already exists");
               });

       brand.setName(normalizedName);
       Brand savedBrand= brandRepository.save(brand);
       return mapToResponse(savedBrand);

    }
    @Override
    public BrandResponse getBrandById(Long id){

       Brand brand= brandRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Brand Not Found"));
        return  mapToResponse(brand);

   }
    @Override
    public List<BrandResponse> getAllBrands(){

       return brandRepository.findAllByOrderByIdAsc().stream()
                .map(BrandMapper::mapToResponse)
                .toList();

    }

    @Override
    public void deleteBrandById(Long id){

      Brand brand =  brandRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Brand Not Found"));

        brandRepository.deleteById(id);
    }

}
