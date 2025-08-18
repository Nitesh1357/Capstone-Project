package com.project.Capstone.ecommerce.service.Impl;

import com.project.Capstone.common.MailService;
import com.project.Capstone.ecommerce.dto.request.OrderRequestDto;
import com.project.Capstone.ecommerce.dto.response.OrderResponseDto;
import com.project.Capstone.ecommerce.mapper.OrderMapper;
import com.project.Capstone.ecommerce.model.Customer;
import com.project.Capstone.ecommerce.model.Order;
import com.project.Capstone.ecommerce.model.OrderStatus;
import com.project.Capstone.ecommerce.repository.CustomerRepository;
import com.project.Capstone.ecommerce.repository.OrderRepository;
import com.project.Capstone.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final MailService emailService;

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto dto) {
        log.info("Placing new order for customerId: {}", dto.getCustomerId());

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + dto.getCustomerId()));

        Order order = Order.builder()
                .productName(dto.getProductName())
                .amount(dto.getAmount())
                .status(OrderStatus.PLACED)
                .createdAt(LocalDateTime.now())
                .customer(customer)
                .build();

        Order saved = orderRepository.save(order);
        log.info("Order placed successfully with ID: {}", saved.getId());

        // Send confirmation email
        String subject = "Order Confirmation - #" + saved.getId();
        String body = "Dear " + customer.getName() + ",\n\nYour order for " + saved.getProductName() +
                " of amount ₹" + saved.getAmount() + " has been placed successfully!\n\nThanks,\nCapstone Team";
        emailService.sendMail(customer.getEmail(), subject, body);

        return OrderMapper.toDto(saved);
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        log.info("Fetching all orders...");
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toDto)
                .collect(toList());
    }
}
