package com.elshoura.lokit.repository;

import com.elshoura.lokit.models.entitys.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCartId(Long id);

    Optional<CartItem> findByCart_IdAndVariant_Id(Long id, Long variantId);

}

