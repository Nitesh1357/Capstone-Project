package com.project.Capstone.blog.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogCommentResponse {
    private Long id;
    private String content;
    private String author;
    private LocalDateTime createdAt;
}
