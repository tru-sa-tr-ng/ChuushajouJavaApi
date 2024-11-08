package com.app.chuushajou.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;

    @Column (name = "ticket_type")
    private String ticket_type;

    @Column (name = "issue_date")
    private Date issue_date;

    @Column (name = "expiry_date")
    private Date expiry_date;

    @Column(name = "total")
    private Integer total;





}

