package com.app.chuushajou.services;

import com.app.chuushajou.dtos.ParkingDTO;
import com.app.chuushajou.models.Parking;
import com.app.chuushajou.models.Vehicle;
import com.app.chuushajou.repositories.ParkingRepository;
import com.app.chuushajou.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingService {
    private final ParkingRepository parkingRepository;
    private final VehicleRepository vehicleRepository;


    public Integer addVehicleToParking(long parkingId, long vehicleId) {
        Optional<Parking> parkingOpt = parkingRepository.findById(parkingId);
        if (parkingOpt.isEmpty()) throw new RuntimeException("Parking not found");

        Parking parking = parkingOpt.get();
        if (parking.getVehicles().size() >= parking.getAvaiableSlot()) throw new RuntimeException("Parking is full");

        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(vehicleId);
        if (vehicleOpt.isEmpty()) throw new RuntimeException("Vehicle not found");

        Vehicle vehicle = vehicleOpt.get();
        if (parking.getVehicles().contains(vehicle)) throw new RuntimeException("Vehicle already parked");

        vehicle.setParking(parking);
        parking.getVehicles().add(vehicle);
        parkingRepository.save(parking);

        return parking.getAvaiableSlot();
    }

    public Integer removeVehicleFromParking(long parkingId, long vehicleId) {
        Optional<Parking> parkingOpt = parkingRepository.findById(parkingId);
        if (parkingOpt.isEmpty()) throw new RuntimeException("Parking not found");

        Parking parking = parkingOpt.get();
        if (parking.getVehicles().size() >= parking.getAvaiableSlot()) throw new RuntimeException("Parking is full");

        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(vehicleId);
        if (vehicleOpt.isEmpty()) throw new RuntimeException("Vehicle not found");

        Vehicle vehicle = vehicleOpt.get();
        if (!parking.getVehicles().contains(vehicle)) throw new RuntimeException("Vehicle is not parked in this parking");

        vehicle.setParking(null);
        parking.getVehicles().remove(vehicle);
        parkingRepository.save(parking);

        return parking.getAvaiableSlot();
    }

    public int getVehicleCountInParking(long parkingId) {
        Optional<Parking> parkingOpt = parkingRepository.findById(parkingId);
        if (parkingOpt.isEmpty()) throw new RuntimeException("Parking not found");

        Parking parking = parkingOpt.get();
        return parking.getVehicles().size();
    }

    public ParkingDTO getParking(long parkingId){
        Optional<Parking> parkingOpt = parkingRepository.findById(parkingId);
        if (parkingOpt.isEmpty()) throw new RuntimeException("Parking not found");

        Parking parking = parkingOpt.get();
        return ParkingDTO.getParkingFromModel(parking);
    }

}
