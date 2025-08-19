package com.project.Capstone.jobportal.controller;

import com.project.Capstone.jobportal.dto.request.EmployerRequestDto;
import com.project.Capstone.jobportal.dto.response.EmployerResponseDto;
import com.project.Capstone.jobportal.service.EmployerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/jobportal/employers")
@RequiredArgsConstructor
public class EmployerController {

    private final EmployerService employerService;

    @PostMapping
    public EmployerResponseDto createEmployer(@RequestBody EmployerRequestDto employerDto) {
        log.info("POST /employers -> {}", employerDto);
        return employerService.createEmployer(employerDto);
    }

    @GetMapping("/{id}")
    public EmployerResponseDto getEmployerById(@PathVariable Long id) {
        log.info("GET /employers/{}", id);
        return employerService.getEmployerById(id);
    }

    @GetMapping
    public List<EmployerResponseDto> getAllEmployers() {
        log.info("GET /employers");
        return employerService.getAllEmployers();
    }
}
