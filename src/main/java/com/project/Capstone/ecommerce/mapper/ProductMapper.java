package com.project.Capstone.ecommerce.mapper;

import com.project.Capstone.ecommerce.dto.response.ProductResponseDto;
import com.project.Capstone.ecommerce.model.Product;

public class ProductMapper {
    public static ProductResponseDto toDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .categoryName(product.getCategory().getName())
                .build();
    }
}

