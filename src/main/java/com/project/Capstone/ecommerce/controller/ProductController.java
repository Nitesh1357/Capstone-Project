package com.project.Capstone.ecommerce.controller;

import com.project.Capstone.ecommerce.dto.request.ProductRequestDto;
import com.project.Capstone.ecommerce.dto.response.ProductResponseDto;
import com.project.Capstone.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto dto) {
        log.info("Creating product with name: {}", dto.getName());
        ProductResponseDto response = productService.createProduct(dto);
        log.info("Product created with ID: {}", response.getId());
        return response;
    }

    @GetMapping("/public")
    public List<ProductResponseDto> getAllPublic() {
        log.info("Fetching all public products");
        return productService.getAllProducts();
    }
}
