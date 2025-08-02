package com.project.Capstone.blog.dto.request;

import lombok.Data;

@Data
public class AuthorRequestDto {
    private String name;
    private String email;
    private String bio;
}
