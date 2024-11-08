package com.app.chuushajou.repositories;

import com.app.chuushajou.models.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Override
    Page<Vehicle> findAll(Pageable pageable);
}