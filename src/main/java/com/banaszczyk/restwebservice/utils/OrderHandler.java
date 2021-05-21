package com.banaszczyk.restwebservice.utils;

import com.banaszczyk.restwebservice.model.Order;
import com.banaszczyk.restwebservice.model.QuantityProduct;
import com.banaszczyk.restwebservice.service.OrderService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderHandler {

    private final OrderService orderService;

    public OrderHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    public Order save(List<QuantityProduct> products){
        Order order = new Order();
        order.setQuantityProducts(products);
        return orderService.save(order);
    }

}
