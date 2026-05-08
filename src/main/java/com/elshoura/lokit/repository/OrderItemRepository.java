package com.elshoura.lokit.repository;

import com.elshoura.lokit.models.entitys.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


     List<OrderItem> findByOrderId(Long orderId);


}
