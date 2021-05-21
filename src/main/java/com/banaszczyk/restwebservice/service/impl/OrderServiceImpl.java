package com.banaszczyk.restwebservice.service.impl;

import com.banaszczyk.restwebservice.model.Order;
import com.banaszczyk.restwebservice.repository.OrderRepository;
import com.banaszczyk.restwebservice.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderService) {
        this.orderRepository = orderService;
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        return orderRepository.findById(orderId);
    }


    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
