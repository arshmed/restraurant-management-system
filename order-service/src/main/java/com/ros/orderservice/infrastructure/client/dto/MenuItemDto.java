package com.ros.orderservice.infrastructure.client.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MenuItemDto {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}