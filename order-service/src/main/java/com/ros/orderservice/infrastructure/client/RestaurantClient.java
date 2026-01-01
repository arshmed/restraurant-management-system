package com.ros.orderservice.infrastructure.client;

import com.ros.orderservice.infrastructure.client.dto.MenuItemDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface RestaurantClient {

    @GetExchange("/{restaurantId}/items/{productId}")
    MenuItemDto getMenuItemDetails(@PathVariable String restaurantId,
                                  @PathVariable String productId);
}