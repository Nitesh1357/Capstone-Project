package com.project.Capstone.jobportal.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class EmployerRequestDto {
    private String companyName;
    private String contactEmail;
    private List<String> postedJobs;
}
