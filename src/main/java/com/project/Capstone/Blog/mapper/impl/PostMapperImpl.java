package com.project.Capstone.blog.mapper.impl;

import com.project.Capstone.blog.dto.response.PostResponse;
import com.project.Capstone.blog.mapper.PostMapper;
import com.project.Capstone.blog.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapperImpl implements PostMapper {
    @Override
    public PostResponse toResponse(Post post) {
        PostResponse response = new PostResponse();
        response.setId(post.getId());
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setCreatedAt(post.getCreatedAt());
        return response;
    }
}
