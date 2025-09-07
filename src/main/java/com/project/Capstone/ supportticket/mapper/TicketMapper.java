package com.project.Capstone.supportticket.mapper;

import com.project.Capstone.supportticket.dto.response.TicketResponseDto;
import com.project.Capstone.supportticket.model.Ticket;

public class TicketMapper {
    public static TicketResponseDto toDto(Ticket ticket) {
        return TicketResponseDto.builder()
                .id(ticket.getId())
                .subject(ticket.getSubject())
                .message(ticket.getDescription())
                .status(ticket.getStatus().name())
                .username(ticket.getCustomer().getFullName()) // ✅ FIXED here
                .createdAt(ticket.getCreatedAt())
                .build();
    }
}
