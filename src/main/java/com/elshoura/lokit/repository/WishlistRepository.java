package com.elshoura.lokit.repository;

import com.elshoura.lokit.models.entitys.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<WishlistItem,Long> {

    List<WishlistItem> findByUserId(Long userId);

    Optional<WishlistItem> findByUserIdOrderByCreatedAtDesc(Long userId, Long productId);

    Boolean existsByUserIdAndProductId(Long userId, Long productId);

}
