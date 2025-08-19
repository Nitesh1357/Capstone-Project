package com.project.Capstone.jobportal.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateResponse {
    private Long candidateId;
    private String fullName;
    private String email;
    private String phone;
    private String resumeLink;
}
