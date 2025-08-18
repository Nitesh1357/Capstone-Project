package com.project.Capstone.ecommerce.service;

import com.project.Capstone.ecommerce.dto.request.OrderRequestDto;
import com.project.Capstone.ecommerce.dto.response.OrderResponseDto;

import java.util.List;

public interface OrderService {
    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto);
    List<OrderResponseDto> getAllOrders();
}
