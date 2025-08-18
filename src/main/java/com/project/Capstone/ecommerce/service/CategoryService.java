package com.project.Capstone.ecommerce.service;

import com.project.Capstone.ecommerce.dto.request.CategoryRequestDto;
import com.project.Capstone.ecommerce.dto.response.CategoryResponseDto;
import com.project.Capstone.ecommerce.mapper.CategoryMapper;
import com.project.Capstone.ecommerce.model.Category;
import com.project.Capstone.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponseDto createCategory(CategoryRequestDto dto) {
        if (categoryRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Category already exists with name: " + dto.getName());
        }

        Category category = CategoryMapper.toEntity(dto);
        Category saved = categoryRepository.save(category);
        return CategoryMapper.toDto(saved);
    }

    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toDto)
                .toList();
    }
}
