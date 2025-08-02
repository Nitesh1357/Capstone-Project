package com.project.Capstone.blog.mapper;

import com.project.Capstone.blog.dto.response.PostResponseDto;
import com.project.Capstone.blog.model.Post;
import com.project.Capstone.blog.model.Tag;

public class PostMapper {
    public static PostResponseDto toDto(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authorName(post.getAuthor().getName())
                .authorEmail(post.getAuthor().getEmail())
                .categoryName(post.getCategory().getName())
                .tags(post.getTags().stream().map(Tag::getName).toList())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
