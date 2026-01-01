package com.ros.orderservice.domain.model;

public enum OrderStatus {
    PENDING,
    CONFIRMED,
    IN_DELIVERY,
    DELIVERED,
    CANCELLED
}