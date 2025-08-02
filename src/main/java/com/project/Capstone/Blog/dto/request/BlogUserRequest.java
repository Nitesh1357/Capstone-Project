package com.project.Capstone.blog.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogUserRequest {

    @Schema(example = "John Doe")
    private String fullName;

    @Schema(example = "johndoe@gmail.com")
    private String email;

    @Schema(example = "password123")
    private String password;
}
