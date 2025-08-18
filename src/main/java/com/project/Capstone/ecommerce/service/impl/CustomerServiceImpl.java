package com.project.Capstone.ecommerce.service.Impl;

import com.project.Capstone.common.MailService;
import com.project.Capstone.ecommerce.dto.request.CustomerRequestDto;
import com.project.Capstone.ecommerce.dto.response.CustomerResponseDto;
import com.project.Capstone.ecommerce.model.Customer;
import com.project.Capstone.ecommerce.repository.CustomerRepository;
import com.project.Capstone.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final MailService mailService;

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto dto) {
        Customer customer = Customer.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .build();

        Customer saved = customerRepository.save(customer);
        log.info("Customer created with ID: {}", saved.getId());

        // Send welcome mail
        mailService.sendMail(
                saved.getEmail(),
                "Welcome to our E-Commerce App",
                "Hi " + saved.getName() + ", your account has been created successfully!"
        );

        return CustomerResponseDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .email(saved.getEmail())
                .address(saved.getAddress())
                .build();
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> CustomerResponseDto.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .email(customer.getEmail())
                        .address(customer.getAddress())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        log.info("Customer deleted with ID: {}", id);
    }
}
