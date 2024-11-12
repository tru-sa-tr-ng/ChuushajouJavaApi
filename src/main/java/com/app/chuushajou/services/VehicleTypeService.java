package com.app.chuushajou.services;


import com.app.chuushajou.dtos.VehicleTypeDTO;
import com.app.chuushajou.models.VehicleType;
import com.app.chuushajou.repositories.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleTypeService {
    private final VehicleTypeRepository vehicleTypeRepository;

    public Page<VehicleTypeDTO> getVehicleTypes(PageRequest pageRequest){
        return vehicleTypeRepository.findAll(pageRequest).map(VehicleTypeDTO::getVehicleTypeFromModel);
    }

    public VehicleTypeDTO getVehicleTypeById(long id) throws Exception {
        return VehicleTypeDTO.getVehicleTypeFromModel(vehicleTypeRepository.findById(id).orElseThrow());
    }

    public VehicleTypeDTO addVehicleType(VehicleTypeDTO vehicleTypeDTO) {
        VehicleType vehicleType = new VehicleType();

        vehicleType.setTitle(vehicleTypeDTO.getTitle());
        vehicleType.setCost(vehicleTypeDTO.getCost());

        return VehicleTypeDTO.getVehicleTypeFromModel(vehicleTypeRepository.save(vehicleType));
    }

    public VehicleTypeDTO updateVehicleType(VehicleTypeDTO vehicleTypeDTO, long id) {
        VehicleType vehicleType = vehicleTypeRepository.findById(id).orElseThrow();

        vehicleType.setTitle(vehicleTypeDTO.getTitle());
        vehicleType.setCost(vehicleTypeDTO.getCost());

        return VehicleTypeDTO.getVehicleTypeFromModel(vehicleTypeRepository.save(vehicleType));
    }

    public void removeVehicleType(long id) {
        vehicleTypeRepository.deleteById(id);
    }
}
