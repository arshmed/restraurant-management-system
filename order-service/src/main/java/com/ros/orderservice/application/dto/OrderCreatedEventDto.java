package com.ros.orderservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEventDto {
    private Long orderId;
    private String restaurantId;
    private Long customerId;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
}