package com.app.chuushajou.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
@Table(name = "tickets")
public class Ticket extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn (name = "ticket_type_id")
    private TicketType ticketType;

    @Column (name = "issue_date")
    private LocalDateTime issueDate;

    @Column(name = "total")
    private Integer total;
}

