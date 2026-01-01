package com.ros.reportingservice.domain.repository;

import com.ros.reportingservice.domain.model.SalesSummary;
import java.util.List;

public interface SalesSummaryRepository {

    SalesSummary save(SalesSummary summary);
    
    List<SalesSummary> findAll();
    
    // Raporlama için gelecekte gerekecek metotlar:
    // List<SalesSummary> findByRestaurantId(Long restaurantId);
    // List<SalesSummary> findByDateRange(LocalDateTime start, LocalDateTime end);
}