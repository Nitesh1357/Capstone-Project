package com.project.Capstone.supportticket.model;

import com.project.Capstone.ecommerce.model.Customer;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<Reply> replies;
}
