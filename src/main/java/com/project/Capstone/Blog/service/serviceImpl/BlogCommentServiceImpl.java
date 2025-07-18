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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogCommentServiceImpl implements BlogCommentService {

    private final BlogCommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public BlogCommentResponse create(Long userId, BlogCommentRequest request) {
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BlogComment comment = BlogComment.builder()
                .content(request.getContent())
                .post(post)
                .user(user)
                .build();

        BlogComment saved = commentRepository.save(comment);

        BlogCommentResponse response = mapper.map(saved, BlogCommentResponse.class);
        response.setAuthor(user.getFullName());
        return response;
    }

    @Override
    public List<BlogCommentResponse> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(comment -> {
                    BlogCommentResponse res = mapper.map(comment, BlogCommentResponse.class);
                    res.setAuthor(comment.getUser().getFullName());
                    return res;
                })
                .collect(Collectors.toList());
    }
}
