package com.project.Capstone.blog.service;

import com.project.Capstone.blog.dto.request.PostRequestDto;
import com.project.Capstone.blog.dto.response.PostResponseDto;

import java.util.List;

public interface PostService {
    PostResponseDto createPost(PostRequestDto dto);
    List<PostResponseDto> getAllPosts();
    PostResponseDto getPostById(Long id);
    PostResponseDto updatePost(Long id, PostRequestDto dto);
    void deletePost(Long id);
}
