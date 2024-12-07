package com.app.chuushajou.controllers;

import com.app.chuushajou.libs.ResMap;
import com.app.chuushajou.models.Parking;
import com.app.chuushajou.services.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking")
public class ParkingCtrl {
    private final ParkingService parkingService;

    @PostMapping("/{parkingId}/add_vehicle/{vehicleId}")
    public ResponseEntity<?> addVehicleToParking(@PathVariable Long parkingId, @PathVariable Long vehicleId) {
        try {
            parkingService.addVehicleToParking(parkingId, vehicleId);

            int slotsUsed = parkingService.getVehicleCountInParking(parkingId);
            int slotsRemain = Parking.slotsAvailable - slotsUsed;

            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", "Vehicle added to parking, slots remain: " + slotsRemain)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }

    @DeleteMapping("/{parkingId}/remove_vehicle/{vehicleId}")
    public ResponseEntity<?> removeVehicleFromParking(@PathVariable Long parkingId, @PathVariable Long vehicleId) {
        try {
            parkingService.removeVehicleFromParking(parkingId, vehicleId);

            int slotsUsed = parkingService.getVehicleCountInParking(parkingId);
            int slotsRemain = Parking.slotsAvailable - slotsUsed;

            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", "Vehicle remove from parking, slots remain: " + slotsRemain)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }
}
