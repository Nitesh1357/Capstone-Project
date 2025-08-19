package com.project.Capstone.jobportal.controller;

import com.project.Capstone.jobportal.dto.request.JobRequestDto;
import com.project.Capstone.jobportal.dto.response.JobResponseDto;
import com.project.Capstone.jobportal.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public JobResponseDto postJob(@RequestBody JobRequestDto dto) {
        log.info("Posting a new job with title: {}", dto.getTitle());
        JobResponseDto response = jobService.postJob(dto);
        log.info("Job posted successfully with ID: {}", response.getId());
        return response;
    }

    @GetMapping
    public List<JobResponseDto> getAllJobs() {
        log.info("Fetching all available jobs");
        List<JobResponseDto> jobs = jobService.browseJobs();
        log.info("Found {} jobs", jobs.size());
        return jobs;
    }
}
