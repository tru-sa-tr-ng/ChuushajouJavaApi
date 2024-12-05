package com.app.chuushajou.repositories;

import com.app.chuushajou.models.Parking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
    @Override
    Page<Parking> findAll(Pageable pageable);
}
