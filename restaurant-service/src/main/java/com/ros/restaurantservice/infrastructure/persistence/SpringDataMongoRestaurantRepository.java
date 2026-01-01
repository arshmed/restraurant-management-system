package com.ros.restaurantservice.infrastructure.persistence;

import com.ros.restaurantservice.domain.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMongoRestaurantRepository extends MongoRepository<Restaurant, String> {
    // MongoRepository<Restaurant, String>
    // String -> Çünkü @Id alanımız String tipinde
    
    // Spring Data MongoDB, metot isimlerinden sorgu üretebilir:
    // Optional<Restaurant> findByName(String name);
}