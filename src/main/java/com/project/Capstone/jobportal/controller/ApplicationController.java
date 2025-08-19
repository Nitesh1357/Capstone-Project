package com.project.Capstone.jobportal.controller;

import com.project.Capstone.jobportal.dto.request.ApplicationRequestDto;
import com.project.Capstone.jobportal.dto.response.ApplicationResponseDto;
import com.project.Capstone.jobportal.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/apply")
    public void applyToJob(@RequestBody ApplicationRequestDto dto) {
        log.info("Received job application from candidate ID: {} for job ID: {}", dto.getCandidateId(), dto.getJobId());
        applicationService.apply(dto);
        log.info("Application submitted successfully.");
    }

    @GetMapping("/me")
    public List<ApplicationResponseDto> getMyApplications(@RequestParam Long candidateId) {
        log.info("Fetching applications for candidate ID: {}", candidateId);
        List<ApplicationResponseDto> applications = applicationService.myApplications(candidateId);
        log.info("Found {} applications for candidate ID: {}", applications.size(), candidateId);
        return applications;
    }
}
