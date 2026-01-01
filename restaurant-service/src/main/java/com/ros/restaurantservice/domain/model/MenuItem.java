package com.ros.restaurantservice.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class MenuItem {
    
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    
    public MenuItem(String name, String description, BigDecimal price) {
        this.id = java.util.UUID.randomUUID().toString(); // Gömülü belgeler için manuel ID
        this.name = name;
        this.description = description;
        this.price = price;
    }
}