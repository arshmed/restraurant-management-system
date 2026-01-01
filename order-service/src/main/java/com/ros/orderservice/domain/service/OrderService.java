package com.ros.orderservice.domain.service;

import com.ros.orderservice.domain.model.Order;

public interface OrderService {
    Order createOrder(Order order);
}