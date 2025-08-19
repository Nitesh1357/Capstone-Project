package com.project.Capstone.jobportal.dto.request;

import lombok.Data;

@Data
public class JobRequestDto {
    private String title;
    private String description;
    private String location;
    private String type;
    private Long employerId;
}
