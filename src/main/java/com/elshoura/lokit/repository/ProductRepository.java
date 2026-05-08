package com.elshoura.lokit.repository;

import com.elshoura.lokit.models.entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> , JpaSpecificationExecutor<Product> {

    List<Product> findByBrandIdOrderByIdAsc(Long brandId);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByDepartmentId(Long departmentId);

    Optional<Product> findProductById(Long id);

    List<Product> findTop8ByActiveTrueOrderByCreatedAtDesc();

    List<Product> findByDepartmentIdAndActiveTrueOrderByCreatedAtDesc(Long departmentId);

    List<Product> findTop12ByActiveTrueOrderByCreatedAtDesc();
 }
