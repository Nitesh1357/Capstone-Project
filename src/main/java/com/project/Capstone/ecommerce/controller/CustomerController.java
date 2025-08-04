package com.project.Capstone.ecommerce.controller;

import com.project.Capstone.ecommerce.dto.request.CustomerRequestDto;
import com.project.Capstone.ecommerce.dto.response.CustomerResponseDto;
import com.project.Capstone.ecommerce.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/customers")
@RequiredArgsConstructor
@Tag(name = "Customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CustomerResponseDto createCustomer(@RequestBody CustomerRequestDto request) {
        return customerService.createCustomer(request);
    }

    @GetMapping
    public List<CustomerResponseDto> getAll() {
        return customerService.getAllCustomers();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
