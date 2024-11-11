package com.app.chuushajou.services;
import com.app.chuushajou.repositories.VehicleRepository;
import com.app.chuushajou.responses.VehicleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;


    public Page<VehicleResponse> getVehicles(PageRequest pageRequest){
        return vehicleRepository.findAll(pageRequest).map(VehicleResponse::getVehicleFromModel);
    }

}
