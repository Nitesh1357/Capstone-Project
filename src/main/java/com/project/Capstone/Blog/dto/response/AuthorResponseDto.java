package com.project.Capstone.blog.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorResponseDto {
    private Long id;
    private String name;
    private String email;
    private String bio;
}
