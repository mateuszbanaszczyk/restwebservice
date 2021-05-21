package com.banaszczyk.restwebservice.repository;

import com.banaszczyk.restwebservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long>{
    Optional<Order> findById (Long orderId);
}
