package com.app.chuushajou.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "parking_space")
public class ParkingSpace extends BaseModel {
    @Column(name = "space_name", unique = true)
    private String spaceName;

    @Column(name = "total_position")
    private Integer totalPosition;

    @Column(name = "total_vehicle")
    private Integer totalVehicle;

    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

    public Integer getRemaining(){
        return  totalPosition - totalVehicle;
    }

}
