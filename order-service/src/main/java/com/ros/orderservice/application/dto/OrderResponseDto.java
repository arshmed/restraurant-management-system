package com.ros.orderservice.application.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long id;
    private String restaurantId;
    private Long customerId;
    private List<OrderItemDto> items;
    private BigDecimal totalPrice;
    private String status;
    private LocalDateTime createdAt;
}