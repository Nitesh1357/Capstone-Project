package com.project.Capstone.blog.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String authorName;
    private String authorEmail;
    private String categoryName;
    private List<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
