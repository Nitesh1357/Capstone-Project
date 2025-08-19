package com.project.Capstone.jobportal.dto.request;

import lombok.Data;

@Data
public class ApplicationRequestDto {
    private Long jobId;
    private Long candidateId;
    private String resumeUrl;
}
