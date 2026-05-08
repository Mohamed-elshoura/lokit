package com.elshoura.lokit.repository;

import com.elshoura.lokit.models.entitys.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {

    Boolean existsByName(String name);

    Optional<Brand> findByNameIgnoreCase(String name);


    List<Brand> findAllByOrderByIdAsc();

}
