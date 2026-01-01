package com.ros.restaurantservice.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "restaurants")
public class Restaurant {
    
    @Id
    private String id;
    private Long ownerId;
    private String name;
    private String address;
    private boolean active = true;
    
    private List<Category> menu = new ArrayList<>();

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }
}