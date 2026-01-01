package com.ros.reportingservice.domain.service;

import com.ros.reportingservice.application.dto.OrderCreatedEventDto;

public interface ReportingService {
    
    void processNewOrder(OrderCreatedEventDto event);
    byte[] generateSimpleSalesReport() throws Exception;
}