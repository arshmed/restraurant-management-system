package com.ros.orderservice.domain.repository;

import com.ros.orderservice.domain.model.Order;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(Long id);
    // List<Order> findByCustomerId(Long customerId);
    // List<Order> findByRestaurantId(Long restaurantId);
}
