package com.elshoura.lokit.utils.mapper;

import com.elshoura.lokit.models.dto.request.ProductRequest;
import com.elshoura.lokit.models.dto.response.ProductResponse;
import com.elshoura.lokit.models.entitys.*;

public class ProductMapper {

    private ProductMapper() {
        throw new IllegalStateException(" Utility class can't be instantiated");
    }

  public static Product toProduct(ProductRequest productRequest, Brand brand, Category category, Department department , Material material) {

        return Product.builder()
                .name(productRequest.name().trim())
                .description(productRequest.description().trim())
                .brand(brand)
                .category(category)
                .department(department)
                .material(material)
                .active(true)
                .build();
  }
  public static void update(Product product,
                                     ProductRequest productRequest,
                                     Brand brand,
                                     Category category,
                                     Department department ,
                                     Material material) {

        product.setName(productRequest.name().trim());
        product.setDescription(productRequest.description().trim());
        product.setBrand(brand);
        product.setCategory(category);
        product.setDepartment(department);
        product.setMaterial(material);

  }

  public static ProductResponse mapToProductResponse(Product product) {

     return ProductResponse.builder()
             .id(product.getId())
             .name(product.getName())
             .description(product.getDescription())
             .brandName(product.getBrand().getName())
             .categoryName(product.getCategory().getName())
             .materialName(product.getMaterial().getName())
             .departmentName(product.getDepartment().getName())
             .active(product.getActive())
             .build();

  }
}
