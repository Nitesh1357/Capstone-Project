package com.project.Capstone.jobportal.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class JobResponseDto {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String type;
    private String employerName;
    private LocalDateTime postedAt;
}
