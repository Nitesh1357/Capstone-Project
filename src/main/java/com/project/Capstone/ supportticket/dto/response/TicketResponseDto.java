package com.project.Capstone.supportticket.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TicketResponseDto {
    private Long id;
    private String subject;
    private String message;
    private String status;
    private String username;
    private LocalDateTime createdAt;
}
