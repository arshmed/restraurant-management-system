package com.ros.restaurantservice.domain.service;

import com.ros.restaurantservice.application.dto.CreateMenuItemRequestDto;
import com.ros.restaurantservice.domain.model.Category;
import com.ros.restaurantservice.domain.model.MenuItem;
import com.ros.restaurantservice.domain.model.Restaurant;

public interface RestaurantService {

    Restaurant createRestaurant(Restaurant restaurant);

    Restaurant getRestaurantById(String id);

    Restaurant addCategoryToRestaurant(String restaurantId, String categoryName);

    Restaurant addMenuItemToCategory(String restaurantId, String categoryId, CreateMenuItemRequestDto itemDto);

    MenuItem getMenuItem(String restaurantId, String productId);
}