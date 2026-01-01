package com.ros.restaurantservice.application.controller;

import com.ros.restaurantservice.application.dto.CreateCategoryRequestDto;
import com.ros.restaurantservice.application.dto.CreateMenuItemRequestDto;
import com.ros.restaurantservice.application.dto.CreateRestaurantRequestDto;
import com.ros.restaurantservice.application.dto.RestaurantResponseDto;
import com.ros.restaurantservice.application.mapper.RestaurantMapper;
import com.ros.restaurantservice.domain.model.MenuItem;
import com.ros.restaurantservice.domain.model.Restaurant;
import com.ros.restaurantservice.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper mapper;

    @PostMapping
    public ResponseEntity<RestaurantResponseDto> createRestaurant(
            @RequestBody CreateRestaurantRequestDto dto) {

        Restaurant restaurantToCreate = mapper.createRestaurantDtoToRestaurant(dto);
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurantToCreate);

        return new ResponseEntity<>(mapper.restaurantToRestaurantResponseDto(createdRestaurant), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantById(@PathVariable String id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(mapper.restaurantToRestaurantResponseDto(restaurant));
    }

    @GetMapping("/{restaurantId}/items/{productId}")
    public ResponseEntity<MenuItem> getMenuItemDetails(
            @PathVariable String restaurantId,
            @PathVariable String productId) {

        MenuItem item = restaurantService.getMenuItem(restaurantId, productId);
        return ResponseEntity.ok(item);
    }

    @PostMapping("/{id}/categories")
    public ResponseEntity<RestaurantResponseDto> addCategory(
            @PathVariable String id,
            @RequestBody CreateCategoryRequestDto dto) {

        Restaurant updatedRestaurant = restaurantService.addCategoryToRestaurant(id, dto.getName());

        return ResponseEntity.ok(mapper.restaurantToRestaurantResponseDto(updatedRestaurant));
    }

    @PostMapping("/{restaurantId}/categories/{categoryId}/items")
    public ResponseEntity<RestaurantResponseDto> addMenuItem(
            @PathVariable String restaurantId,
            @PathVariable String categoryId,
            @RequestBody CreateMenuItemRequestDto dto) {

        Restaurant updatedRestaurant = restaurantService.addMenuItemToCategory(restaurantId, categoryId, dto);

        return ResponseEntity.ok(mapper.restaurantToRestaurantResponseDto(updatedRestaurant));
    }

}