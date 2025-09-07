package com.project.Capstone.supportticket.dto.request;

import lombok.Data;

@Data
public class TicketRequestDto {
    private Long customerId;
    private String subject;
    private String description;
}
