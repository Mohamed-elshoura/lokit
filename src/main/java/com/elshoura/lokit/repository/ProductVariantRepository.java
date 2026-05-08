package com.elshoura.lokit.repository;

import com.elshoura.lokit.models.dto.request.ProductVariantRequest;
import com.elshoura.lokit.models.entitys.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant,Long> {

    List<ProductVariant> findByProductId(Long productId);

    boolean existsByProductIdAndSizeIdAndColorId(Long productId, Long sizeId, Long colorId);

    boolean existsBySku(String sku);}
