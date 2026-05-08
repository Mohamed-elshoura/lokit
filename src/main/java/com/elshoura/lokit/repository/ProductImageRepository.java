package com.elshoura.lokit.repository;

import com.elshoura.lokit.models.entitys.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {

    List<ProductImage> findByProductId(Long productId);

    boolean existsByProductIdAndIsMainTrue(Long productId);

    Optional<ProductImage> findFirstByProductIdAndIsMainTrue(Long productId);

    Optional<ProductImage> findFirstByProductIdOrderByIdAsc(Long productId);

    List<ProductImage> findByProductIdOrderByIdAsc(Long productId);


}
