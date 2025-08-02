package com.project.Capstone.blog.controller;

import com.project.Capstone.blog.dto.request.BlogCommentRequest;
import com.project.Capstone.blog.dto.response.BlogCommentResponse;
import com.project.Capstone.blog.service.BlogCommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog/comments")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Slf4j
public class BlogCommentController {

    private final BlogCommentService commentService;

    @PostMapping
    public ResponseEntity<BlogCommentResponse> addComment(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody BlogCommentRequest request
    ) {
        Long userId = Long.parseLong(userDetails.getUsername()); // You may need to replace this with custom principal

        log.info("User {} is adding a comment to post {}", userId, request.getPostId());
        BlogCommentResponse response = commentService.create(userId, request);
        log.info("Comment added successfully by user {}", userId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<BlogCommentResponse>> getPostComments(@PathVariable Long postId) {
        log.info("Fetching comments for post ID: {}", postId);
        List<BlogCommentResponse> comments = commentService.getCommentsByPost(postId);
        log.info("Found {} comments for post ID {}", comments.size(), postId);
        return ResponseEntity.ok(comments);
    }
}
