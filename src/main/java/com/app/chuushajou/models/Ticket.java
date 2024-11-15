package com.app.chuushajou.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Date;
import java.time.LocalDateTime;

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

    @PrePersist
    protected void onCreate() {
        this.issue_date = Date.valueOf(LocalDateTime.now().toLocalDate());
        this.expiry_date = Date.valueOf(LocalDateTime.now().toLocalDate().plusYears(1));
    }

}

