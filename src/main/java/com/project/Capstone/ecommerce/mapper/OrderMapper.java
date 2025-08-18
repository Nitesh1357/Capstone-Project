package com.project.Capstone.ecommerce.mapper;

import com.project.Capstone.ecommerce.dto.response.OrderResponseDto;
import com.project.Capstone.ecommerce.model.Order;

public class OrderMapper {
    public static OrderResponseDto toDto(Order order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .productName(order.getProductName())
                .amount(order.getAmount())
                .status(order.getStatus().name())
                .createdAt(order.getCreatedAt())
                .customerName(order.getCustomer().getName())
                .build();
    }
}
