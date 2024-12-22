package com.app.chuushajou.repositories;
import com.app.chuushajou.models.VehicleType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
    @Override
    List<VehicleType> findAll();
}


