package com.app.chuushajou.services;


import com.app.chuushajou.dtos.VehicleTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public interface VehicleTypeService {

    Page<VehicleTypeDTO> getVehicleTypes(PageRequest pageRequest);

    VehicleTypeDTO getVehicleTypeById(long id);

    VehicleTypeDTO createVehicleType(VehicleTypeDTO vehicleTypeDTO);

    VehicleTypeDTO updateVehicleType(VehicleTypeDTO vehicleTypeDTO, long id);

    VehicleTypeDTO deleteVehicleType(long id);
}
