package com.elshoura.lokit.utils.mapper;

import com.elshoura.lokit.models.entitys.ProductVariant;
import com.elshoura.lokit.models.dto.request.ProductVariantRequest;
import com.elshoura.lokit.models.dto.response.ProductVariantResponse;
import com.elshoura.lokit.models.entitys.Color;
import com.elshoura.lokit.models.entitys.Product;
import com.elshoura.lokit.models.entitys.Size;

public class ProductVariantMapper {

    private ProductVariantMapper() {
        throw new IllegalStateException(" Utility class can't be instantiated");
    }


    public static ProductVariant toProductVariant(ProductVariantRequest productVariantRequest, Product product, Color color , Size size) {

        return  ProductVariant.builder()
                .product(product)
                .color(color)
                .size(size)
                .sku(productVariantRequest.sku())
                .price(productVariantRequest.price())
                .stock(productVariantRequest.stock())
                .build();
    }

    public static ProductVariantResponse mapToVariantResponse (ProductVariant productVariant) {
        return ProductVariantResponse.builder()
                .id(productVariant.getId())
                .productName(productVariant.getProduct().getName())
                .sizeName(productVariant.getSize().getName())
                .colorName(productVariant.getColor().getName())
                .sku(productVariant.getSku())
                .price(productVariant.getPrice())
                .stock(productVariant.getStock())
                .build();
    }
    public static void updateProductVariant(ProductVariant productVariant,
                                            Product product,
                                            ProductVariantRequest productVariantRequest,
                                            Color color,
                                            Size size) {

        productVariant.setProduct(product);
        productVariant.setSize(size);
        productVariant.setColor(color);
        productVariant.setSku(productVariantRequest.sku());
        productVariant.setPrice(productVariantRequest.price());
        productVariant.setStock(productVariantRequest.stock());
    }
}
