package com.ros.userservice.domain.repository;

import com.ros.userservice.domain.model.User;
import java.util.Optional;

public interface UserRepository {
    
    Optional<User> findByEmail(String email);
    
    User save(User user);
    
    boolean existsByEmail(String email);
}