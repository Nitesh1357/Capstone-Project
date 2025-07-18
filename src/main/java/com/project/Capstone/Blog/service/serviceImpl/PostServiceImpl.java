package com.project.Capstone.blog.service.serviceImpl;

import com.project.Capstone.blog.dto.request.CreatePostRequest;
import com.project.Capstone.blog.dto.response.PostResponse;
import com.project.Capstone.blog.model.*;
import com.project.Capstone.blog.repository.*;
import com.project.Capstone.blog.service.PostService;
import com.project.Capstone.model.User;
import com.project.Capstone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostResponse createPost(CreatePostRequest request) {
        User author = userRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        List<Tag> tags = tagRepository.findAllById(request.getTagIds());

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(author)
                .category(category)
                .tags(tags)
                .build();

        Post savedPost = postRepository.save(post);

        return mapToResponse(savedPost);
    }

    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return mapToResponse(post);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Page<PostResponse> getAllPosts(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Post> posts = postRepository.findAll(pageable);

        return posts.map(this::mapToResponse);
    }

    private PostResponse mapToResponse(Post post) {
        PostResponse response = modelMapper.map(post, PostResponse.class);
        response.setAuthorName(post.getAuthor().getFullName());
        response.setCategoryName(post.getCategory().getName());
        response.setTagNames(post.getTags().stream().map(Tag::getName).collect(Collectors.toList()));
        return response;
    }
}
