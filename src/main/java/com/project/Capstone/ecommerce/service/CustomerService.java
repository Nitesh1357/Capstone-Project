package com.project.Capstone.ecommerce.service;

import com.project.Capstone.ecommerce.dto.request.CustomerRequestDto;
import com.project.Capstone.ecommerce.dto.response.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto createCustomer(CustomerRequestDto requestDto);
    List<CustomerResponseDto> getAllCustomers();
    void deleteCustomer(Long id);
}
