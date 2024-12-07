package com.app.chuushajou.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ticket_types")
public class TicketType extends BaseModel {

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private Integer cost;

    @Column(name="activeDays")
    private Integer days;


}

