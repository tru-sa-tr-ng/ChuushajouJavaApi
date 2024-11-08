package com.app.chuushajou.models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="customer_name")
    private String customer_name;

    @Column(name="phone_number")
    private String phone_number;

    @Column(name = "address")
    private String address;

}
