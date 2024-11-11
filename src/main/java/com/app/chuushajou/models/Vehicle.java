package com.app.chuushajou.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Table(name = "vehicles")
public class Vehicle extends BaseModel {
    @Column(name = "license_plate")
    private String license;

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private VehicleType type;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JoinColumn(name = "img")
    private String img;


}