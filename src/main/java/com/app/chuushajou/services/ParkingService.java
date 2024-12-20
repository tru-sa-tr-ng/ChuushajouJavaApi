package com.app.chuushajou.services;

import com.app.chuushajou.dtos.ParkingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public interface ParkingService {


    Integer addVehicleToParking(long parkingId, long vehicleId);

    Integer removeVehicleFromParking(long parkingId, long vehicleId);

    Integer getVehicleCountInParking(long parkingId);

    Page<ParkingDTO> getParkings(PageRequest pageRequest);

}
