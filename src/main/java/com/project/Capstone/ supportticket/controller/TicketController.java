package com.project.Capstone.supportticket.controller;

import com.project.Capstone.supportticket.dto.request.ReplyRequestDto;
import com.project.Capstone.supportticket.dto.request.TicketRequestDto;
import com.project.Capstone.supportticket.dto.response.TicketResponseDto;
import com.project.Capstone.supportticket.model.Status;
import com.project.Capstone.supportticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public TicketResponseDto createTicket(@RequestBody TicketRequestDto dto) {
        log.info("Creating new ticket for customer ID: {}", dto.getCustomerId());
        TicketResponseDto response = ticketService.createTicket(dto);
        log.info("Ticket created with ID: {}", response.getId());
        return response;
    }

    @GetMapping("/mine")
    public List<TicketResponseDto> getMyTickets(@RequestParam Long customerId) {
        log.info("Fetching tickets for customer ID: {}", customerId);
        return ticketService.getTicketsByCustomer(customerId);
    }

    @PatchMapping("/{id}/status")
    public void updateTicketStatus(@PathVariable Long id, @RequestParam Status status) {
        log.info("Updating ticket status. Ticket ID: {}, New Status: {}", id, status);
        ticketService.updateTicketStatus(id, status);
        log.info("Ticket status updated successfully.");
    }

    @PostMapping("/{id}/reply")
    public void replyToTicket(@PathVariable Long id, @RequestBody ReplyRequestDto dto) {
        log.info("Adding reply to ticket ID: {} by {}", id, dto.getRepliedBy());
        ticketService.addReply(id, dto);
        log.info("Reply added successfully to ticket ID: {}", id);
    }
}
