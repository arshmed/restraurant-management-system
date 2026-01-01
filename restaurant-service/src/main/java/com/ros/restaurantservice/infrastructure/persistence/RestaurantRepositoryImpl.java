package com.ros.restaurantservice.infrastructure.persistence;

import com.ros.restaurantservice.domain.model.Restaurant;
import com.ros.restaurantservice.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final SpringDataMongoRestaurantRepository mongoRepository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return mongoRepository.save(restaurant);
    }

    @Override
    public Optional<Restaurant> findById(String id) {
        return mongoRepository.findById(id);
    }
}