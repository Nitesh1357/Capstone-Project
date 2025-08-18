package com.project.Capstone.ecommerce.dto.request;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private Long categoryId;
}
