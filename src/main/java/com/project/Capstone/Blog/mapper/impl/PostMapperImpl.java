package com.project.Capstone.blog.mapper.impl;

import com.project.Capstone.blog.dto.request.PostRequestDto;
import com.project.Capstone.blog.dto.response.PostResponseDto;
import com.project.Capstone.blog.model.BlogUser;
import com.project.Capstone.blog.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapperImpl {

    public PostResponseDto toDto(Post post) {
        BlogUser author = post.getAuthor();

        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .authorName(author.getName())
                .authorEmail(author.getEmail())
                .build();
    }

    public Post toEntity(PostRequestDto dto, BlogUser author) {
        return Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(author)
                .build();
    }
}
