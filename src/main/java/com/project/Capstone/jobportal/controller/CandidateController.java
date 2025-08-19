package com.project.Capstone.jobportal.controller;

import com.project.Capstone.jobportal.dto.request.CandidateRequest;
import com.project.Capstone.jobportal.dto.response.CandidateResponse;
import com.project.Capstone.jobportal.service.CandidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/jobportal/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    public CandidateResponse createCandidate(@RequestBody CandidateRequest request) {
        return candidateService.createCandidate(request);
    }

    @GetMapping
    public List<CandidateResponse> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public CandidateResponse getCandidateById(@PathVariable Long id) {
        return candidateService.getCandidateById(id);
    }
}
