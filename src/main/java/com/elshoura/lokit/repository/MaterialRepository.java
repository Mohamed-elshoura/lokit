package com.elshoura.lokit.repository;

import com.elshoura.lokit.models.entitys.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material,Long> {

    Optional<Material> findByNameIgnoreCase(String name);

    List<Material> findAllByOrderByIdAsc();
}
