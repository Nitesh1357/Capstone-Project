package com.project.Capstone.blog.service.serviceImpl;

import com.project.Capstone.blog.dto.request.AuthorRequestDto;
import com.project.Capstone.blog.dto.response.AuthorResponseDto;
import com.project.Capstone.blog.model.BlogUser;
import com.project.Capstone.blog.repository.BlogUserRepository;
import com.project.Capstone.blog.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final BlogUserRepository blogUserRepository;

    @Override
    public AuthorResponseDto createAuthor(AuthorRequestDto dto) {
        BlogUser author = BlogUser.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .bio(dto.getBio())
                .build();

        BlogUser saved = blogUserRepository.save(author);

        return AuthorResponseDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .email(saved.getEmail())
                .bio(saved.getBio())
                .build();
    }

    @Override
    public List<AuthorResponseDto> getAllAuthors() {
        return blogUserRepository.findAll().stream()
                .map(author -> AuthorResponseDto.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .email(author.getEmail())
                        .bio(author.getBio())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponseDto getAuthorById(Long id) {
        BlogUser author = blogUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));

        return AuthorResponseDto.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .bio(author.getBio())
                .build();
    }

    @Override
    public void deleteAuthor(Long id) {
        if (!blogUserRepository.existsById(id)) {
            throw new RuntimeException("Author not found with id: " + id);
        }
        blogUserRepository.deleteById(id);
    }
}
