package com.app.chuushajou.services;
import com.app.chuushajou.dtos.CustomerDTO;
import com.app.chuushajou.dtos.VehicleDTO;
import com.app.chuushajou.models.Vehicle;
import com.app.chuushajou.repositories.CustomerRepository;
import com.app.chuushajou.repositories.VehicleRepository;
import com.app.chuushajou.repositories.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final CustomerRepository customerRepository;


    public Page<VehicleDTO> getVehicles(Long customerId, Long typeId, PageRequest pageRequest){
        return vehicleRepository.find(customerId, typeId, pageRequest).map(VehicleDTO::getVehicleFromModel);
    }

    public VehicleDTO getVehicleById(long id) throws Exception {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow();
        return VehicleDTO.getVehicleFromModel(vehicle);
    }

    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();

        vehicle.setLicense(vehicleDTO.getLicense());
        vehicle.setColor(vehicleDTO.getColor());
        vehicle.setType(vehicleTypeRepository.getReferenceById(vehicleDTO.getTypeId()));
        vehicle.setCustomer(customerRepository.getReferenceById(vehicleDTO.getCustomerId()));
        vehicle.setImg(vehicleDTO.getImg());

        return VehicleDTO.getVehicleFromModel(vehicleRepository.save(vehicle));
    }

    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO, long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow();

        if (vehicleDTO.getLicense() != null)
            vehicle.setLicense(vehicleDTO.getLicense());

        if (vehicleDTO.getColor() != null)
            vehicle.setColor(vehicleDTO.getColor());

        if (vehicleDTO.getTypeId() != 0)
            vehicle.setType(vehicleTypeRepository.getReferenceById(vehicleDTO.getTypeId()));

        if (vehicleDTO.getCustomerId() != 0)
            vehicle.setCustomer(customerRepository.getReferenceById(vehicleDTO.getCustomerId()));

        if (vehicleDTO.getImg() != null)
            vehicle.setImg(vehicleDTO.getImg());

        return VehicleDTO.getVehicleFromModel(vehicleRepository.save(vehicle));
    }


    public VehicleDTO deleteVehicle(long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow();

        vehicleRepository.delete(vehicle);

        return VehicleDTO.getVehicleFromModel(vehicle);
    }
}
