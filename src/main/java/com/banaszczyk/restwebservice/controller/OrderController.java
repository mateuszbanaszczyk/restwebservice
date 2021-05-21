package com.banaszczyk.restwebservice.controller;

import com.banaszczyk.restwebservice.service.OrderService;
import com.banaszczyk.restwebservice.utils.OrderHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    private final OrderService orderService;

    OrderHandler orderHandler;

    public OrderController(OrderService orderService, OrderHandler orderHandler) {
        this.orderService = orderService;
        this.orderHandler = orderHandler;
            }

    @GetMapping("/order/{orderId}")
    ResponseEntity<?> getOrderById(@PathVariable("orderId") Long orderId) {
        log.info("reading given order");
        return orderService.findById(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
