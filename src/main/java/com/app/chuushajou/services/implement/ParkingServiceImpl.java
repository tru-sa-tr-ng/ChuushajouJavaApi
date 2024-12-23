package com.app.chuushajou.services.implement;


import com.app.chuushajou.dtos.ParkingDTO;
import com.app.chuushajou.models.Parking;
import com.app.chuushajou.models.Vehicle;
import com.app.chuushajou.repositories.ParkingRepository;
import com.app.chuushajou.repositories.VehicleRepository;
import com.app.chuushajou.services.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {
    private final ParkingRepository parkingRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public Integer addVehicleToParking(long parkingId, long vehicleId) {
        Optional<Parking> parkingOpt = parkingRepository.findById(parkingId);
        if (parkingOpt.isEmpty()) throw new RuntimeException("Parking not found");

        Parking parking = parkingOpt.get();
        if (parking.getAvaiableSlot() == 0) throw new RuntimeException("Parking is full");

        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(vehicleId);
        if (vehicleOpt.isEmpty()) throw new RuntimeException("Vehicle not found");

        Vehicle vehicle = vehicleOpt.get();
        if (parking.getVehicles().contains(vehicle)) throw new RuntimeException("Vehicle already parked");

        vehicle.setParking(parking);
        parking.getVehicles().add(vehicle);
        parkingRepository.save(parking);

        return parking.getAvaiableSlot();
    }

    @Override
    public Integer removeVehicleFromParking(long parkingId, long vehicleId) {
        Optional<Parking> parkingOpt = parkingRepository.findById(parkingId);
        if (parkingOpt.isEmpty()) throw new RuntimeException("Parking not found");

        Parking parking = parkingOpt.get();

        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(vehicleId);
        if (vehicleOpt.isEmpty()) throw new RuntimeException("Vehicle not found");

        Vehicle vehicle = vehicleOpt.get();
        if (!parking.getVehicles().contains(vehicle)) throw new RuntimeException("Vehicle is not parked in this parking");

        vehicle.setParking(null);
        parking.getVehicles().remove(vehicle);
        parkingRepository.save(parking);

        return parking.getAvaiableSlot();
    }

    @Override
    public Integer getVehicleCountInParking(long parkingId) {
        Optional<Parking> parkingOpt = parkingRepository.findById(parkingId);
        if (parkingOpt.isEmpty()) throw new RuntimeException("Parking not found");

        Parking parking = parkingOpt.get();
        return parking.getVehicles().size();
    }

    @Override
    public Page<ParkingDTO> getParkings(PageRequest pageRequest){

        return parkingRepository.findAll(pageRequest).map(ParkingDTO::getParkingFromModel);
    }

    @Override
    public ParkingDTO updateParking(ParkingDTO parkingDTO, long id) {
        Parking parking = parkingRepository.findById(id).orElseThrow();

        if (parkingDTO.getName() != null)
            parking.setName(parkingDTO.getName());

        if (parkingDTO.getLocation() != null)
            parking.setLocation(parkingDTO.getLocation());

        if (parkingDTO.getMaxSlot() != null)
            parking.setMaxSlot(parkingDTO.getMaxSlot());


        return ParkingDTO.getParkingFromModel(parkingRepository.save(parking));
    }

    @Override
    public ParkingDTO deleteParking(long id) {
        Parking parking = parkingRepository.findById(id).orElseThrow();

        parkingRepository.delete(parking);
        return ParkingDTO.getParkingFromModel(parking);
    }

    @Override
    public ParkingDTO getParkingById(long id) {
        Parking parking = parkingRepository.findById(id).orElseThrow();
        return ParkingDTO.getParkingFromModel(parking);
    }

    @Override
    public ParkingDTO createParking(ParkingDTO parkingDTO) {
        Parking parking = new Parking();
        parking.setName(parkingDTO.getName());
        parking.setLocation(parkingDTO.getLocation());
        parking.setMaxSlot(parkingDTO.getMaxSlot());

        return ParkingDTO.getParkingFromModel(parkingRepository.save(parking));
    }

}

