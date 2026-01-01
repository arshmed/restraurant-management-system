package com.ros.orderservice.application.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private String productId;
    private Integer quantity;
}