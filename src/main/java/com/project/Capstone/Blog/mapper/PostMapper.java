package com.project.Capstone.blog.mapper;

import com.project.Capstone.blog.model.Post;
import com.project.Capstone.blog.dto.response.PostResponse;

public interface PostMapper {
    PostResponse toResponse(Post post);
}
