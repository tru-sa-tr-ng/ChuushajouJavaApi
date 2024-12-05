package com.app.chuushajou.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "vehicles")
public class Vehicle extends BaseModel {
    @Column(name = "license_plate", unique = true)
    private String license;

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private VehicleType type;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    @JoinColumn(name = "img")
    private String img;


}