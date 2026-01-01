package com.ros.orderservice.infrastructure.persistence;

import com.ros.orderservice.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaOrderRepository extends JpaRepository<Order, Long> {

}