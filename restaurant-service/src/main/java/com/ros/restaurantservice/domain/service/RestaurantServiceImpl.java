package com.ros.restaurantservice.domain.service;

import com.ros.restaurantservice.application.dto.CreateMenuItemRequestDto;
import com.ros.restaurantservice.domain.exception.CategoryNotFoundException;
import com.ros.restaurantservice.domain.exception.MenuItemNotFoundException;
import com.ros.restaurantservice.domain.exception.ResourceAccessDeniedException;
import com.ros.restaurantservice.domain.exception.RestaurantNotFoundException;
import com.ros.restaurantservice.domain.model.Category;
import com.ros.restaurantservice.domain.model.MenuItem;
import com.ros.restaurantservice.domain.model.Restaurant;
import com.ros.restaurantservice.domain.repository.RestaurantRepository;
import com.ros.restaurantservice.infrastructure.context.UserContext; // YENİ
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List; // YENİ

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserContext userContext;

    private void checkRole(String role) {
        List<String> roles = userContext.getRoles();
        if (roles == null || roles.isEmpty() || !roles.contains(role)) {
            throw new ResourceAccessDeniedException("Access denied. Required role: " + role);
        }
    }

    private void checkOwnership(Restaurant restaurant) {
        Long ownerId = userContext.getUserId();
        if (!restaurant.getOwnerId().equals(ownerId)) {
            throw new ResourceAccessDeniedException("You do not have permission to modify this restaurant.");
        }
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        checkRole("ROLE_RESTAURANT_OWNER");
        restaurant.setOwnerId(userContext.getUserId());

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getRestaurantById(String id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + id));
    }

    @Override
    public Restaurant addCategoryToRestaurant(String restaurantId, String categoryName) {
        checkRole("ROLE_RESTAURANT_OWNER");

        Restaurant restaurant = getRestaurantById(restaurantId);
        checkOwnership(restaurant);

        Category category = new Category(categoryName);
        restaurant.getMenu().add(category);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant addMenuItemToCategory(String restaurantId, String categoryId, CreateMenuItemRequestDto itemDto) {
        checkRole("ROLE_RESTAURANT_OWNER");

        Restaurant restaurant = getRestaurantById(restaurantId);
        checkOwnership(restaurant);

        Category category = restaurant.getMenu().stream()
                .filter(c -> c.getId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + categoryId));

        MenuItem item = new MenuItem(
                itemDto.getName(),
                itemDto.getDescription(),
                itemDto.getPrice()
        );

        category.getItems().add(item);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public MenuItem getMenuItem(String restaurantId, String productId) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        return restaurant.getMenu().stream()
                .flatMap(category -> category.getItems().stream())
                .filter(item -> item.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new MenuItemNotFoundException("MenuItem not found with id: " + productId));
    }
}