package com.ros.restaurantservice.application.dto;

import com.ros.restaurantservice.domain.model.Category;
import lombok.Data;
import java.util.List;

@Data
public class RestaurantResponseDto {
    private String id;
    private String name;
    private String address;
    private boolean active;
    private List<Category> menu;
}