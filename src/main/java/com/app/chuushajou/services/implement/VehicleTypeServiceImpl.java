package com.app.chuushajou.services.implement;

import com.app.chuushajou.dtos.VehicleTypeDTO;
import com.app.chuushajou.models.VehicleType;
import com.app.chuushajou.repositories.VehicleTypeRepository;
import com.app.chuushajou.services.VehicleTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleTypeServiceImpl implements VehicleTypeService {
    private final VehicleTypeRepository vehicleTypeRepository;

    @Override
    public Page<VehicleTypeDTO> getVehicleTypes(PageRequest pageRequest){
        return vehicleTypeRepository.findAll(pageRequest).map(VehicleTypeDTO::getVehicleTypeFromModel);
    }

    @Override
    public VehicleTypeDTO getVehicleTypeById(long id) {
        return VehicleTypeDTO.getVehicleTypeFromModel(vehicleTypeRepository.findById(id).orElseThrow());
    }

    @Override
    public VehicleTypeDTO createVehicleType(VehicleTypeDTO vehicleTypeDTO) {
        VehicleType vehicleType = new VehicleType();

        vehicleType.setTitle(vehicleTypeDTO.getTitle());
        vehicleType.setCost(vehicleTypeDTO.getCost());

        return VehicleTypeDTO.getVehicleTypeFromModel(vehicleTypeRepository.save(vehicleType));
    }

    @Override
    public VehicleTypeDTO updateVehicleType(VehicleTypeDTO vehicleTypeDTO, long id) {
        VehicleType vehicleType = vehicleTypeRepository.findById(id).orElseThrow();

        if (vehicleTypeDTO.getTitle() != null)
            vehicleType.setTitle(vehicleTypeDTO.getTitle());

        if (vehicleTypeDTO.getCost() != null)
            vehicleType.setCost(vehicleTypeDTO.getCost());

        return VehicleTypeDTO.getVehicleTypeFromModel(vehicleTypeRepository.save(vehicleType));
    }

    @Override
    public VehicleTypeDTO deleteVehicleType(long id) {
        VehicleType vehicleType = vehicleTypeRepository.findById(id).orElseThrow();

        vehicleTypeRepository.delete(vehicleType);

        return VehicleTypeDTO.getVehicleTypeFromModel(vehicleType);
    }
}
