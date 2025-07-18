package com.project.Capstone.blog.service.serviceImpl;

import com.project.Capstone.blog.dto.request.CategoryRequest;
import com.project.Capstone.blog.dto.response.CategoryResponse;
import com.project.Capstone.blog.model.Category;
import com.project.Capstone.blog.repository.CategoryRepository;
import com.project.Capstone.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public CategoryResponse create(CategoryRequest request) {
        Category category = Category.builder().name(request.getName()).build();
        return mapper.map(categoryRepository.save(category), CategoryResponse.class);
    }

    @Override
    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream()
                .map(cat -> mapper.map(cat, CategoryResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}

