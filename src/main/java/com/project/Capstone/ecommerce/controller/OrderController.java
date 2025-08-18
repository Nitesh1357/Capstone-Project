package com.project.Capstone.ecommerce.controller;

import com.project.Capstone.ecommerce.dto.request.OrderRequestDto;
import com.project.Capstone.ecommerce.dto.response.OrderResponseDto;
import com.project.Capstone.ecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Order Controller", description = "APIs for placing and retrieving orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderRequestDto dto) {
        log.info("Received request to place order");
        return ResponseEntity.ok(orderService.placeOrder(dto));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        log.info("Received request to fetch all orders");
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
