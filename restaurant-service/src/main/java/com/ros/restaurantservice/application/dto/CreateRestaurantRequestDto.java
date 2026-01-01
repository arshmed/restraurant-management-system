package com.ros.restaurantservice.application.dto;

import lombok.Data;

@Data
public class CreateRestaurantRequestDto {
    private String name;
    private String address;
}