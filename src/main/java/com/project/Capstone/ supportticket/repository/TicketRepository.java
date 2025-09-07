package com.project.Capstone.supportticket.repository;

import com.project.Capstone.supportticket.model.Ticket;
import com.project.Capstone.ecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCustomer(Customer customer);
}
