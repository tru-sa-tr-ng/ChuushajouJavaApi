package com.app.chuushajou.repositories;

import com.app.chuushajou.models.ParkingSpace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
    @Override
    Page<ParkingSpace> findAll(Pageable pageable);
}
