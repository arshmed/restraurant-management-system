package com.ros.orderservice.domain.service;

import com.ros.orderservice.application.dto.OrderCreatedEventDto; // YENİ
import com.ros.orderservice.domain.model.Order;
import com.ros.orderservice.domain.model.OrderItem;
import com.ros.orderservice.domain.repository.OrderRepository;
import com.ros.orderservice.infrastructure.client.RestaurantClient;
import com.ros.orderservice.infrastructure.client.dto.MenuItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate; // YENİ
import org.springframework.beans.factory.annotation.Value; // YENİ
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;
    private final RestaurantClient restaurantClient;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Override
    @Transactional
    public Order createOrder(Order order) {

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderItem item : order.getItems()) {
            MenuItemDto menuItem = restaurantClient.getMenuItemDetails(
                    order.getRestaurantId(),
                    item.getProductId()
            );

            if (menuItem == null || menuItem.getPrice() == null) {
                throw new RuntimeException("Product not found or price is invalid: " + item.getProductId());
            }

            item.setPrice(menuItem.getPrice());

            BigDecimal itemTotal = item.getPrice().multiply(new BigDecimal(item.getQuantity()));
            totalPrice = totalPrice.add(itemTotal);
            item.setOrder(order);
        }

        order.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(order);

        OrderCreatedEventDto eventDto = new OrderCreatedEventDto(
                savedOrder.getId(),
                savedOrder.getRestaurantId(),
                savedOrder.getCustomerId(),
                savedOrder.getTotalPrice(),
                savedOrder.getCreatedAt()
        );

        rabbitTemplate.convertAndSend(exchangeName, routingKey, eventDto);

        return savedOrder;
    }
}