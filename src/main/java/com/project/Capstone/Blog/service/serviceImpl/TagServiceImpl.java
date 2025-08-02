package com.project.Capstone.blog.service.serviceImpl;

import com.project.Capstone.blog.dto.request.TagRequest;
import com.project.Capstone.blog.dto.response.TagResponse;
import com.project.Capstone.blog.model.Tag;
import com.project.Capstone.blog.repository.TagRepository;
import com.project.Capstone.blog.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ModelMapper mapper;

    @Override
    public TagResponse create(TagRequest request) {
        log.info("Creating tag with name: {}", request.getName());
        Tag tag = Tag.builder().name(request.getName()).build();
        Tag saved = tagRepository.save(tag);
        log.info("Tag saved with ID: {}", saved.getId());
        return mapper.map(saved, TagResponse.class);
    }

    @Override
    public List<TagResponse> getAll() {
        log.info("Fetching all tags...");
        List<TagResponse> tags = tagRepository.findAll().stream()
                .map(t -> mapper.map(t, TagResponse.class))
                .collect(Collectors.toList());
        log.info("Total tags fetched: {}", tags.size());
        return tags;
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting tag with ID: {}", id);
        tagRepository.deleteById(id);
        log.info("Tag deleted successfully");
    }
}
