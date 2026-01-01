package com.ros.restaurantservice.domain.repository;

import com.ros.restaurantservice.domain.model.Restaurant;
import java.util.Optional;

public interface RestaurantRepository {
    
    Restaurant save(Restaurant restaurant);
    
    Optional<Restaurant> findById(String id);
    
    // İleride eklenecekler:
    // Optional<Restaurant> findByName(String name);
    // List<Restaurant> findAllActive();
}