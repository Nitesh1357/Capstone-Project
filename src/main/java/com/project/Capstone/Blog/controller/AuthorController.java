package com.project.Capstone.blog.controller;

import com.project.Capstone.blog.dto.request.AuthorRequestDto;
import com.project.Capstone.blog.dto.response.AuthorResponseDto;
import com.project.Capstone.blog.service.AuthorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
@Tag(name = "Blog Author APIs")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@RequestBody AuthorRequestDto dto) {
        return ResponseEntity.ok(authorService.createAuthor(dto));
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok("Author deleted successfully");
    }
}
