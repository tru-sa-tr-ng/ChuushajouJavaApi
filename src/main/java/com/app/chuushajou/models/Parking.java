package com.app.chuushajou.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "parking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles;

    @Column(name = "parking_name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "max_slot")
    private Integer maxSlot;

    public Integer getAvaiableSlot(){
        if (maxSlot == null || maxSlot - vehicles.size() < 0) return 0;
        return maxSlot - vehicles.size();
    }
}
