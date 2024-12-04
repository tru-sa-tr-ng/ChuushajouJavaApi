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

    public VehicleTypeDTO createVehicleType(VehicleTypeDTO vehicleTypeDTO) {
        VehicleType vehicleType = new VehicleType();

        vehicleType.setTitle(vehicleTypeDTO.getTitle());

        return VehicleTypeDTO.getVehicleTypeFromModel(vehicleTypeRepository.save(vehicleType));
    }

    public VehicleTypeDTO updateVehicleType(VehicleTypeDTO vehicleTypeDTO, long id) {
        VehicleType vehicleType = vehicleTypeRepository.findById(id).orElseThrow();

        if (vehicleTypeDTO.getTitle() != null)
            vehicleType.setTitle(vehicleTypeDTO.getTitle());

        return VehicleTypeDTO.getVehicleTypeFromModel(vehicleTypeRepository.save(vehicleType));
    }

    public VehicleTypeDTO deleteVehicleType(long id) {
        VehicleType vehicleType = vehicleTypeRepository.findById(id).orElseThrow();

        vehicleTypeRepository.delete(vehicleType);

        return VehicleTypeDTO.getVehicleTypeFromModel(vehicleType);
    }
}
