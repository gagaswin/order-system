package com.wti.ordersystem.repository;

import com.wti.ordersystem.enums.ECartStatus;
import com.wti.ordersystem.models.entity.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderCartRepository extends JpaRepository<OrderCart, Long> {
  Optional<OrderCart> findByUserNameAndStatus(String userName, ECartStatus eCartStatus);

//  Optional<OrderCart> findByIdAndStatus(Long id, ECartStatus eCartStatus);
}
