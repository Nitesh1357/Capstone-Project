package com.project.Capstone.jobportal.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class EmployerResponseDto {
    private Long id;
    private String companyName;
    private String contactEmail;
    private List<String> postedJobs;
}
