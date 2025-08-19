package com.project.Capstone.jobportal.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateRequest {
    private String fullName;
    private String email;
    private String phone;
    private String resumeLink;
}
