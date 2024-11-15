package com.app.chuushajou.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "vehicle_types")
public class VehicleType extends BaseModel {

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private Integer cost;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles;
}
