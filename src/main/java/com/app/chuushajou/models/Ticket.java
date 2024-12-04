package com.app.chuushajou.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
public class Ticket extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;


    @Column (name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "total")
    private Long total;

    public void setIssueDate() {
        this.issueDate = LocalDate.now();
        this.total = getVehicle().getType().getCost() * (ChronoUnit.DAYS.between(getCreatedAt().toLocalDate(), issueDate)+1);
    }
}

