package com.elshoura.lokit.service;

import com.elshoura.lokit.errors.exception.AlreadyExistException;
import com.elshoura.lokit.errors.exception.NotFoundException;
import com.elshoura.lokit.models.dto.request.ProductImageRequest;
import com.elshoura.lokit.models.dto.response.ProductImageResponse;
import com.elshoura.lokit.models.entitys.Product;
import com.elshoura.lokit.models.entitys.ProductImage;
import com.elshoura.lokit.repository.ProductImageRepository;
import com.elshoura.lokit.repository.ProductRepository;
import com.elshoura.lokit.utils.mapper.ProductImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.elshoura.lokit.utils.mapper.ProductImageMapper.mapToProductImageResponse;
import static com.elshoura.lokit.utils.mapper.ProductImageMapper.toProductImage;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;

   @Override
   public ProductImageResponse addProductImage(ProductImageRequest request){

       Product product = productRepository.findProductById(request.productId())
               .orElseThrow(()->new NotFoundException("Product not found"));

       Boolean isMain =Boolean.TRUE.equals(request.isMain());

       if (isMain && productImageRepository.existsByProductIdAndIsMainTrue(request.productId())){
           throw new AlreadyExistException("Main image already exists for this product");
       }

     ProductImage productImage= toProductImage(request,product,isMain);

       return mapToProductImageResponse(productImageRepository.save(productImage));
   }

    @Override
    public ProductImageResponse update(Long id, ProductImageRequest request){

        ProductImage image = productImageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product image not found"));

        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        boolean isMain = Boolean.TRUE.equals(request.isMain());

        if (isMain) {
            boolean mainExists = productImageRepository.existsByProductIdAndIsMainTrue(request.productId());
            boolean sameImageIsMain = Boolean.TRUE.equals(image.getIsMain()) &&
                    image.getProduct().getId().equals(request.productId());

            if (mainExists && !sameImageIsMain) {
                throw new AlreadyExistException("Main image already exists for this product");
            }
        }

        image.setProduct(product);
        image.setImageUrl(request.imageUrl());
        image.setIsMain(isMain);

        return mapToProductImageResponse(productImageRepository.save(image));

   }

    @Override
    public List<ProductImageResponse> getByProduct(Long productId) {
        return productImageRepository.findByProductId(productId)
                .stream()
                .map(ProductImageMapper::mapToProductImageResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        ProductImage image = productImageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product image not found"));

        productImageRepository.delete(image);
    }
    @Override
    public ProductImageResponse setMainImage(Long imageId) {

        ProductImage selectedImage = productImageRepository.findById(imageId)
                .orElseThrow(() -> new NotFoundException("Product image not found"));

        Long productId = selectedImage.getProduct().getId();

        List<ProductImage> productImages = productImageRepository.findByProductId(productId);

        productImages.forEach(image -> image.setIsMain(false));

        selectedImage.setIsMain(true);

        productImageRepository.saveAll(productImages);

        return mapToProductImageResponse(selectedImage);
    }

}
