package com.app.chuushajou.services;

import com.app.chuushajou.repositories.ParkingSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingSpaceService {
    private final ParkingSpaceRepository parkingSpaceRepository;


}
