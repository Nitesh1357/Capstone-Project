package com.project.Capstone.blog.controller;

import com.project.Capstone.blog.dto.request.TagRequest;
import com.project.Capstone.blog.dto.response.TagResponse;
import com.project.Capstone.blog.service.TagService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/blog/tags")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TagController {

    private final TagService tagService;
    @PostMapping
    public ResponseEntity<TagResponse> create(@RequestBody TagRequest request) {
        log.info("Creating new tag with name: {}", request.getName());
        TagResponse response = tagService.create(request);
        log.info("Tag created successfully with ID: {}", response.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAll() {
        log.info("Fetching all blog tags");
        List <TagResponse> tags = tagService.getAll();
        log.info ("Total tags fetched : {} ", tags.size());
        return ResponseEntity.ok(tags);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Delete tag with id id:{}", id);
        tagService.delete(id);
        log.info("Tag deleted Successfully with Id: {}", id);
        return ResponseEntity.noContent().build();
    }
}

