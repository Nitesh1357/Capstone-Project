package com.project.Capstone.supportticket.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private String repliedBy; // "USER" or "ADMIN"
    private LocalDateTime repliedAt;

    @ManyToOne
    private Ticket ticket;
}
