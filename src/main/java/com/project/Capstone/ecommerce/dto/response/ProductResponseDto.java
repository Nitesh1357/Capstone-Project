package com.project.Capstone.ecommerce.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder  // ✅ This enables .builder() in ProductMapper
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryName;
}
