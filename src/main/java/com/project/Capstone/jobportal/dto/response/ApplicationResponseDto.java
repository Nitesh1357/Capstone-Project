package com.project.Capstone.jobportal.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApplicationResponseDto {
    private Long id;
    private Long jobId;
    private String jobTitle;
    private String resumeUrl;
    private LocalDateTime appliedAt;
}

