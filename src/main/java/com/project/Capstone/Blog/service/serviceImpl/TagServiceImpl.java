package com.project.Capstone.blog.service.serviceImpl;

import com.project.Capstone.blog.dto.request.TagRequest;
import com.project.Capstone.blog.dto.response.TagResponse;
import com.project.Capstone.blog.model.Tag;
import com.project.Capstone.blog.repository.TagRepository;
import com.project.Capstone.blog.service.TagService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ModelMapper mapper;

    @Override
    public TagResponse create(TagRequest request) {
        Tag tag = Tag.builder().name(request.getName()).build();
        return mapper.map(tagRepository.save(tag), TagResponse.class);
    }

    @Override
    public List<TagResponse> getAll() {
        return tagRepository.findAll().stream()
                .map(t -> mapper.map(t, TagResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}
