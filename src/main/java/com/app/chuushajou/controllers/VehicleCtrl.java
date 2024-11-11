package com.app.chuushajou.controllers;


import com.app.chuushajou.libs.ResMap;
import com.app.chuushajou.dtos.VehicleDTO;
import com.app.chuushajou.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleCtrl {
    private final VehicleService vehicleService;

    @GetMapping("")
    public ResponseEntity<?> getProducts (
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit)
    {

        PageRequest pageRequest = PageRequest.of(page-1, limit);
        Page<VehicleDTO> vehiclePage = vehicleService.getVehicles(pageRequest);




        return ResponseEntity.ok(
                ResMap.of(
                        "status", "success",
                        "filter" , ResMap.of(
                                "currentPage", vehiclePage.getNumber()+1,
                                "totalPage", vehiclePage.getTotalPages(),
                                "limit", vehiclePage.getSize()
                        ),
                        "data", vehiclePage.getContent()
                        )
        );
    }
}
