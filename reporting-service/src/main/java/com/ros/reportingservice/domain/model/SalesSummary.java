package com.ros.reportingservice.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales_summary", schema = "reporting") 
@Data
@NoArgsConstructor
public class SalesSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private String restaurantId;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private LocalDateTime orderCreatedAt;

    public SalesSummary(Long orderId, String restaurantId, Long customerId, BigDecimal totalAmount, LocalDateTime orderCreatedAt) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.orderCreatedAt = orderCreatedAt;
    }
}