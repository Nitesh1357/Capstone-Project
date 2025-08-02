package com.project.Capstone.blog.service.serviceImpl;

import com.project.Capstone.blog.dto.request.CategoryRequest;
import com.project.Capstone.blog.dto.response.CategoryResponse;
import com.project.Capstone.blog.model.BlogCategory;
import com.project.Capstone.blog.repository.BlogCategoryRepository;
import com.project.Capstone.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final BlogCategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public CategoryResponse create(CategoryRequest request) {
        log.info("Creating new category with name: {}", request.getName());

        BlogCategory category = BlogCategory.builder().name(request.getName()).build();
        BlogCategory saved = categoryRepository.save(category);

        log.info("Category created successfully with ID: {}", saved.getId());
        return mapper.map(saved, CategoryResponse.class);
    }

    @Override
    public List<CategoryResponse> getAll() {
        log.info("Fetching all blog categories...");

        List<CategoryResponse> categories = categoryRepository.findAll().stream()
                .map(cat -> mapper.map(cat, CategoryResponse.class))
                .collect(Collectors.toList());

        log.info("Total categories fetched: {}", categories.size());
        return categories;
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting category with ID: {}", id);
        categoryRepository.deleteById(id);
        log.info("Category with ID {} deleted successfully", id);
    }
}
