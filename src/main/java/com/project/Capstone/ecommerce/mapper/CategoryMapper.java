package com.project.Capstone.ecommerce.mapper;

import com.project.Capstone.ecommerce.dto.request.CategoryRequestDto;
import com.project.Capstone.ecommerce.dto.response.CategoryResponseDto;
import com.project.Capstone.ecommerce.model.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryRequestDto dto) {
        return Category.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public static CategoryResponseDto toDto(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
