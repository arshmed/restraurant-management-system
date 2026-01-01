package com.ros.reportingservice.application.listener;

import com.ros.reportingservice.application.dto.OrderCreatedEventDto;
import com.ros.reportingservice.domain.service.ReportingService; // YENİ
import lombok.RequiredArgsConstructor; // YENİ
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private static final Logger log = LoggerFactory.getLogger(OrderEventListener.class);
    private final ReportingService reportingService;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void handleOrderCreatedEvent(OrderCreatedEventDto event) {
        log.info("Received OrderCreatedEvent for orderId: {}", event.getOrderId());
        reportingService.processNewOrder(event);
    }
}