package com.elshoura.lokit.repository;

import com.elshoura.lokit.models.entitys.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

 public interface AddressRepository extends JpaRepository<Address,Long> {

    List<Address> findByUserId(Long userId);

}
