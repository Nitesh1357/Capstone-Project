package com.project.Capstone.blog.service;

import com.project.Capstone.blog.dto.request.BlogCommentRequest;
import com.project.Capstone.blog.dto.response.BlogCommentResponse;

import java.util.List;

public interface BlogCommentService {
    BlogCommentResponse create(Long userId, BlogCommentRequest request);
    List<BlogCommentResponse> getCommentsByPost(Long postId);
}
