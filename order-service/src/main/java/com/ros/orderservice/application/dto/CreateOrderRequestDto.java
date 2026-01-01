package com.ros.orderservice.application.dto;

import lombok.Data;
import java.util.List;

@Data
public class CreateOrderRequestDto {
    private String restaurantId;
    private List<OrderItemDto> items;
}