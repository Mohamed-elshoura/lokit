package com.elshoura.lokit.utils.mapper;

import com.elshoura.lokit.models.dto.request.ProductImageRequest;
import com.elshoura.lokit.models.dto.response.ProductImageResponse;
import com.elshoura.lokit.models.entitys.Product;
import com.elshoura.lokit.models.entitys.ProductImage;

public class ProductImageMapper {

    private ProductImageMapper() {
        throw new IllegalStateException("Utility class Can't be instantiated");
    }

    public static ProductImage toProductImage(ProductImageRequest request, Product product,Boolean isMain) {

        return ProductImage.builder()
                .product(product)
                .imageUrl(request.imageUrl())
                .isMain(isMain)
                .build();
    }
   public static ProductImageResponse mapToProductImageResponse(ProductImage productImage){
       return ProductImageResponse.builder()
               .id(productImage.getId())
               .productId(productImage.getProduct().getId())
               .productName(productImage.getProduct().getName())
               .imageUrl(productImage.getImageUrl())
               .isMain(productImage.getIsMain())
               .build();


   }
}
