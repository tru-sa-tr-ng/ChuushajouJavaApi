package com.app.chuushajou.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "customers")
public class Customer extends BaseModel {
    @Column(name="customer_name")
    private String customer_name;

    @Column(name="phone_number")
    private String phone_number;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles;

    @Column(name = "remain")
    private Long remain;
}
