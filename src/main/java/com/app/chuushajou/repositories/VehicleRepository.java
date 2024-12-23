package com.app.chuushajou.repositories;

import com.app.chuushajou.models.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v WHERE " +
            "(:customer_id IS NULL OR v.customer.id = :customer_id) AND " +
            "(:type_id IS NULL OR v.type.id = :type_id) AND" +
            "(:license IS NULL OR v.license LIKE %:license%)" +
            "ORDER BY v.createdAt DESC")
    Page<Vehicle> find(@Param("customer_id") Long customerId,
                       @Param("type_id") Long vehicleId,
                       @Param("license") String license,
                       Pageable pageable);
    Optional<Vehicle> findByLicense(String licensePlate);

}