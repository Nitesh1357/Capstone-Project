package com.project.Capstone.blog.service.serviceImpl;

import com.project.Capstone.blog.dto.request.PostRequestDto;
import com.project.Capstone.blog.dto.response.PostResponseDto;
import com.project.Capstone.blog.mapper.PostMapper;
import com.project.Capstone.blog.model.BlogUser;
import com.project.Capstone.blog.model.BlogCategory;
import com.project.Capstone.blog.model.Post;
import com.project.Capstone.blog.model.Tag;
import com.project.Capstone.blog.repository.BlogUserRepository;
import com.project.Capstone.blog.repository.BlogCategoryRepository;
import com.project.Capstone.blog.repository.PostRepository;
import com.project.Capstone.blog.repository.TagRepository;
import com.project.Capstone.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final BlogUserRepository blogUserRepository;
    private final BlogCategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final JavaMailSender mailSender;

    @Override
    public PostResponseDto createPost(PostRequestDto dto) {
        BlogUser author = blogUserRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found with ID: " + dto.getAuthorId()));

        BlogCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + dto.getCategoryId()));

        List<Tag> tags = tagRepository.findAllById(dto.getTagIds());

        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(author)
                .category(category)
                .tags(tags)
                .build();

        Post saved = postRepository.save(post);

        log.info(" Post '{}' created by {}", saved.getTitle(), author.getName());
        sendEmail(author.getEmail(), saved.getTitle());

        return PostMapper.toDto(saved);
    }

    @Override
    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        log.info("Fetched all posts: {}", posts.size());
        return posts.stream().map(PostMapper::toDto).toList();
    }

    @Override
    public PostResponseDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));
        log.info("Post fetched with ID {}", id);
        return PostMapper.toDto(post);
    }

    @Override
    public PostResponseDto updatePost(Long id, PostRequestDto dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        if (!post.getAuthor().getId().equals(dto.getAuthorId())) {
            BlogUser newAuthor = blogUserRepository.findById(dto.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Author not found with ID: " + dto.getAuthorId()));
            post.setAuthor(newAuthor);
        }

        BlogCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + dto.getCategoryId()));
        post.setCategory(category);

        List<Tag> tags = tagRepository.findAllById(dto.getTagIds());
        post.setTags(tags);

        Post updated = postRepository.save(post);
        log.info(" Post with ID {} updated", id);
        return PostMapper.toDto(updated);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));
        postRepository.delete(post);
        log.warn(" Post with ID {} deleted", id);
    }

    private void sendEmail(String to, String title) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("New Blog Post Published");
            message.setText("Your blog post titled '" + title + "' has been successfully created.");
            mailSender.send(message);
            log.info("Email sent to {}", to);
        } catch (Exception e) {
            log.error(" Failed to send email to {}: {}", to, e.getMessage());
        }
    }
}
