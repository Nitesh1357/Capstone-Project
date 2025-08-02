package com.project.Capstone.blog.service;

import com.project.Capstone.blog.dto.request.AuthorRequestDto;
import com.project.Capstone.blog.dto.response.AuthorResponseDto;

import java.util.List;

public interface AuthorService {
    AuthorResponseDto createAuthor(AuthorRequestDto dto);

    List<AuthorResponseDto> getAllAuthors();

    AuthorResponseDto getAuthorById(Long id);

    void deleteAuthor(Long id);
}
