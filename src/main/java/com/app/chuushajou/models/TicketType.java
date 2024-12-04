package com.app.chuushajou.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "ticket_types")
public class TicketType extends BaseModel{
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "issue_day", nullable = false)
    private int issueDay;

    @OneToMany(mappedBy = "ticketType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketCost> ticketVehicleCosts;
}
