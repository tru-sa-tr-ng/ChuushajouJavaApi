package com.app.chuushajou.services;
import com.app.chuushajou.dtos.VehicleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service
public interface VehicleService {
    Page<VehicleDTO> getVehicles(Long customerId, Long typeId, String license,PageRequest pageRequest);

    VehicleDTO getVehicleById(long id);

    VehicleDTO createVehicle(VehicleDTO vehicleDTO);

    VehicleDTO updateVehicle(VehicleDTO vehicleDTO, long id);

    VehicleDTO deleteVehicle(long id);

}
