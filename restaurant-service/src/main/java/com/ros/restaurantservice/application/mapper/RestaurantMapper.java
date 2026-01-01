package com.ros.restaurantservice.application.mapper;

import com.ros.restaurantservice.application.dto.CreateCategoryRequestDto;
import com.ros.restaurantservice.application.dto.CreateMenuItemRequestDto;
import com.ros.restaurantservice.application.dto.CreateRestaurantRequestDto;
import com.ros.restaurantservice.application.dto.RestaurantResponseDto;
import com.ros.restaurantservice.domain.model.Category;
import com.ros.restaurantservice.domain.model.MenuItem;
import com.ros.restaurantservice.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "menu", ignore = true)
    Restaurant createRestaurantDtoToRestaurant(CreateRestaurantRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    Category createCategoryDtoToCategory(CreateCategoryRequestDto dto);

    @Mapping(target = "id", ignore = true)
    MenuItem createMenuItemDtoToMenuItem(CreateMenuItemRequestDto dto);

    RestaurantResponseDto restaurantToRestaurantResponseDto(Restaurant restaurant);
}