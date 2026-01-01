package com.ros.restaurantservice.application.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreateMenuItemRequestDto {
    private String name;
    private String description;
    private BigDecimal price;
}