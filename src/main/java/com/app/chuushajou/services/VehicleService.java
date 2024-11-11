package com.app.chuushajou.services;
import com.app.chuushajou.dtos.VehicleDTO;
import com.app.chuushajou.models.Vehicle;
import com.app.chuushajou.models.Vehicle;
import com.app.chuushajou.repositories.VehicleRepository;
import com.app.chuushajou.dtos.VehicleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;


    public Page<VehicleDTO> getVehicles(PageRequest pageRequest){
        return vehicleRepository.findAll(pageRequest).map(VehicleDTO::getVehicleFromModel);
    }

    public VehicleDTO getVehicleById(long id) throws Exception {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow();
        return VehicleDTO.getVehicleFromModel(vehicle);
    }

    public VehicleDTO addVehicle(Vehicle vehicle) {
        return VehicleDTO.getVehicleFromModel(vehicleRepository.save(vehicle));
    }

    public VehicleDTO updateVehicle(Vehicle vehicle) {
        return VehicleDTO.getVehicleFromModel(vehicleRepository.save(vehicle));
    }

    public void removeVehicle(long id) {
        vehicleRepository.deleteById(id);
    }
}
