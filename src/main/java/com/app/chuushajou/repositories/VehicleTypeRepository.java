package com.app.chuushajou.repositories;
import com.app.chuushajou.models.VehicleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
    @Override
    Page<VehicleType> findAll(Pageable pageable);
}


