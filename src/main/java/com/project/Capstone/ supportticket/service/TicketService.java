package com.project.Capstone.supportticket.service;

import com.project.Capstone.ecommerce.model.Customer;
import com.project.Capstone.ecommerce.repository.CustomerRepository;
import com.project.Capstone.supportticket.dto.request.ReplyRequestDto;
import com.project.Capstone.supportticket.dto.request.TicketRequestDto;
import com.project.Capstone.supportticket.dto.response.TicketResponseDto;
import com.project.Capstone.supportticket.mapper.TicketMapper;
import com.project.Capstone.supportticket.model.Reply;
import com.project.Capstone.supportticket.model.Status;
import com.project.Capstone.supportticket.model.Ticket;
import com.project.Capstone.supportticket.repository.ReplyRepository;
import com.project.Capstone.supportticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ReplyRepository replyRepository;
    private final CustomerRepository customerRepository;

    public TicketResponseDto createTicket(TicketRequestDto dto) {
        log.info("Creating ticket for customer ID: {}", dto.getCustomerId());

        Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow();

        Ticket ticket = Ticket.builder()
                .subject(dto.getSubject())
                .description(dto.getDescription())
                .customer(customer)
                .createdAt(LocalDateTime.now())
                .status(Status.OPEN)
                .build();

        Ticket saved = ticketRepository.save(ticket);
        log.info("Ticket created with ID: {}", saved.getId());

        return TicketMapper.toDto(saved);
    }

    public List<TicketResponseDto> getTicketsByCustomer(Long customerId) {
        log.info("Fetching tickets for customer ID: {}", customerId);

        Customer customer = customerRepository.findById(customerId).orElseThrow();
        List<TicketResponseDto> responses = ticketRepository.findByCustomer(customer)
                .stream().map(TicketMapper::toDto).toList();

        log.info("Found {} tickets for customer ID: {}", responses.size(), customerId);
        return responses;
    }

    public void updateTicketStatus(Long ticketId, Status status) {
        log.info("Updating status of ticket ID: {} to {}", ticketId, status);

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        ticket.setStatus(status);
        ticketRepository.save(ticket);

        log.info("Ticket status updated successfully.");
    }

    public void addReply(Long ticketId, ReplyRequestDto dto) {
        log.info("Adding reply to ticket ID: {} by {}", ticketId, dto.getRepliedBy());

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        Reply reply = Reply.builder()
                .message(dto.getMessage())
                .repliedAt(LocalDateTime.now())
                .repliedBy(dto.getRepliedBy())
                .ticket(ticket)
                .build();

        replyRepository.save(reply);

        log.info("Reply added to ticket ID: {}", ticketId);
    }
}
