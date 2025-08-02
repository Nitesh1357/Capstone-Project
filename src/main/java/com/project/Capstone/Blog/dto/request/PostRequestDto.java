package com.project.Capstone.blog.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequestDto {

    @Schema(example = "My First Blog Post")
    private String title;

    @Schema(example = "This is the content of the blog post.")
    private String content;

    @Schema(example = "1")
    private Long authorId;

    @Schema(example = "1")
    private Long categoryId;

    @Schema(description = "List of tag IDs", example = "[1, 2]")
    private List<Long> tagIds;
}
