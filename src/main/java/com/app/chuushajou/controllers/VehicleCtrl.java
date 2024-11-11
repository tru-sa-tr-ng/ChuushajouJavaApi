package com.app.chuushajou.controllers;


import com.app.chuushajou.dtos.CustomerDTO;
import com.app.chuushajou.libs.ResMap;
import com.app.chuushajou.dtos.VehicleDTO;
import com.app.chuushajou.models.Vehicle;
import com.app.chuushajou.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleCtrl {
    private final VehicleService vehicleService;

    @GetMapping("")
    public ResponseEntity<?> getVehicles (
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable("id") Long vehicleId) {
        try {
            VehicleDTO vehicleDTO = vehicleService.getVehicleById(vehicleId);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", vehicleDTO)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addVehicle(@RequestBody Vehicle vehicle) {
        try {
            VehicleDTO vehicleDTO = vehicleService.addVehicle(vehicle);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", vehicleDTO)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicle(@RequestBody Vehicle vehicle) {
        try {
            VehicleDTO vehicleDTO = vehicleService.updateVehicle(vehicle);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", vehicleDTO)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeVehicle(@PathVariable("id") Long vehicleId) {
        try {
            vehicleService.removeVehicle(vehicleId);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "message", "Vehicle removed successfully")
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }
}
