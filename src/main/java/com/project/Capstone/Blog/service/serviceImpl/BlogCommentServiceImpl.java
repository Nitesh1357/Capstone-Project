package com.project.Capstone.blog.service.serviceImpl;

import com.project.Capstone.blog.dto.request.BlogCommentRequest;
import com.project.Capstone.blog.dto.response.BlogCommentResponse;
import com.project.Capstone.blog.model.BlogComment;
import com.project.Capstone.blog.model.Post;
import com.project.Capstone.blog.repository.BlogCommentRepository;
import com.project.Capstone.blog.repository.PostRepository;
import com.project.Capstone.blog.service.BlogCommentService;
import com.project.Capstone.model.User;
import com.project.Capstone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogCommentServiceImpl implements BlogCommentService {

    private final BlogCommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public BlogCommentResponse create(Long userId, BlogCommentRequest request) {
        log.info("Creating comment on postId: {} by userId: {}", request.getPostId(), userId);

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> {
                    log.error("Post not found with id: {}", request.getPostId());
                    return new RuntimeException("Post not found");
                });

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("User not found with id: {}", userId);
                    return new RuntimeException("User not found");
                });

        BlogComment comment = BlogComment.builder()
                .content(request.getContent())
                .post(post)
                .user(user)
                .build();

        BlogComment saved = commentRepository.save(comment);
        log.info("Comment saved successfully with id: {}", saved.getId());

        BlogCommentResponse response = mapper.map(saved, BlogCommentResponse.class);
        response.setAuthor(user.getFullName());
        return response;
    }

    @Override
    public List<BlogCommentResponse> getCommentsByPost(Long postId) {
        log.info("Fetching comments for postId: {}", postId);

        List<BlogCommentResponse> comments = commentRepository.findByPostId(postId).stream()
                .map(comment -> {
                    BlogCommentResponse res = mapper.map(comment, BlogCommentResponse.class);
                    res.setAuthor(comment.getUser().getFullName());
                    return res;
                })
                .collect(Collectors.toList());

        log.info("Total comments fetched for postId {}: {}", postId, comments.size());
        return comments;
    }
}
