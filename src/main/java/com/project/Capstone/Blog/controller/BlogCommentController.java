package com.project.Capstone.blog.controller;

import com.project.Capstone.blog.dto.request.BlogCommentRequest;
import com.project.Capstone.blog.dto.response.BlogCommentResponse;
import com.project.Capstone.blog.service.BlogCommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog/comments")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class BlogCommentController {

    private final BlogCommentService commentService;

    @PostMapping
    public ResponseEntity<BlogCommentResponse> addComment(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody BlogCommentRequest request
    ) {
        // Assume userId is extracted based on your implementation
        Long userId = Long.parseLong(userDetails.getUsername()); // You may need a custom Principal for ID
        return ResponseEntity.ok(commentService.create(userId, request));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<BlogCommentResponse>> getPostComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }
}
