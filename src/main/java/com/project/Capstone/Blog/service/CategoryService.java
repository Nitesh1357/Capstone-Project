package com.project.Capstone.blog.service;

import com.project.Capstone.blog.dto.request.CategoryRequest;
import com.project.Capstone.blog.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest request);
    List<CategoryResponse> getAll();
    void delete(Long id);
}

