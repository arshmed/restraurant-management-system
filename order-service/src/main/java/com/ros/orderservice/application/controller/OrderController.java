package com.ros.orderservice.application.controller;

import com.ros.orderservice.application.dto.CreateOrderRequestDto;
import com.ros.orderservice.application.dto.OrderResponseDto;
import com.ros.orderservice.application.mapper.OrderMapper;
import com.ros.orderservice.domain.model.Order;
import com.ros.orderservice.domain.service.OrderService;
import com.ros.orderservice.infrastructure.context.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final UserContext userContext;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @RequestBody CreateOrderRequestDto requestDto) {

        Long customerId = userContext.getUserId();

        Order orderToCreate = orderMapper.createRequestToOrder(requestDto);
        orderToCreate.setCustomerId(customerId);

        Order createdOrder = orderService.createOrder(orderToCreate);
        OrderResponseDto responseDto = orderMapper.orderToOrderResponse(createdOrder);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}