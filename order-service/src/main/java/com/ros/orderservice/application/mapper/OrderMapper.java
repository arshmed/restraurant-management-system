package com.ros.orderservice.application.mapper;

import com.ros.orderservice.application.dto.CreateOrderRequestDto;
import com.ros.orderservice.application.dto.OrderResponseDto;
import com.ros.orderservice.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "customerId", ignore = true)
    Order createRequestToOrder(CreateOrderRequestDto dto);

    OrderResponseDto orderToOrderResponse(Order order);
}