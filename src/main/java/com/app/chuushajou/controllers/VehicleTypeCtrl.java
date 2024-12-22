package com.app.chuushajou.controllers;


import com.app.chuushajou.dtos.VehicleTypeDTO;
import com.app.chuushajou.libs.ResMap;
import com.app.chuushajou.services.VehicleTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle_types")
@RequiredArgsConstructor
public class VehicleTypeCtrl {
    private final VehicleTypeService vehicleTypeService;

    @GetMapping("")
    public ResponseEntity<?> getVehicleTypes ()
    {
        List<VehicleTypeDTO> vehicleTypeList = vehicleTypeService.getVehicleTypes();

        return ResponseEntity.ok(
                ResMap.of(
                        "status", "success",
                        "data", vehicleTypeList)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleTypeById(@PathVariable("id") Long vehicleTypeId) {
        try {
            VehicleTypeDTO vehicleTypeDTO = vehicleTypeService.getVehicleTypeById(vehicleTypeId);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", vehicleTypeDTO)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createVehicleType(@RequestBody VehicleTypeDTO vehicleTypeDTO) {
        try {
            VehicleTypeDTO newVehicleTypeDTO = vehicleTypeService.createVehicleType(vehicleTypeDTO);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", newVehicleTypeDTO)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicleType(@RequestBody VehicleTypeDTO vehicleTypeDTO, @PathVariable("id") Long vehicleTypeId) {
        try {
            VehicleTypeDTO updatedVehicleTypeDTO = vehicleTypeService.updateVehicleType(vehicleTypeDTO, vehicleTypeId);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", updatedVehicleTypeDTO)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicleType(@PathVariable("id") Long vehicleTypeId) {
        try {
            VehicleTypeDTO removedVehicleTypeDTO = vehicleTypeService.deleteVehicleType(vehicleTypeId);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", removedVehicleTypeDTO)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }
}
