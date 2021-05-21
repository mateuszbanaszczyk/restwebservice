package com.banaszczyk.restwebservice.service;

import com.banaszczyk.restwebservice.model.Order;

import java.util.Optional;

public interface OrderService {
    Optional<Order> findById(Long orderId);

    Order save(Order order);
}
