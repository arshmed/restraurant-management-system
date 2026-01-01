package com.ros.restaurantservice.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Category {
    
    private String id;
    private String name;
    private List<MenuItem> items = new ArrayList<>();
    
    public Category(String name) {
        this.id = java.util.UUID.randomUUID().toString();
        this.name = name;
    }
}