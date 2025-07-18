package com.project.Capstone.blog.service;

import com.project.Capstone.blog.dto.request.TagRequest;
import com.project.Capstone.blog.dto.response.TagResponse;

import java.util.List;

public interface TagService {
    TagResponse create(TagRequest request);
    List<TagResponse> getAll();
    void delete(Long id);
}
