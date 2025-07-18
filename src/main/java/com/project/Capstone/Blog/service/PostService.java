package com.project.Capstone.blog.service;

import com.project.Capstone.blog.dto.request.CreatePostRequest;
import com.project.Capstone.blog.dto.response.PostResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    PostResponse createPost(CreatePostRequest request);
    List<PostResponse> getAllPosts();
    Page<PostResponse> getAllPosts(int page, int size, String sortBy, String direction);

    PostResponse getPostById(Long id);
    void deletePost(Long id);
}
