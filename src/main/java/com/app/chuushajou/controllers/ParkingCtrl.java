package com.app.chuushajou.controllers;

import com.app.chuushajou.dtos.ParkingDTO;
import com.app.chuushajou.libs.PageInfo;
import com.app.chuushajou.libs.ResMap;
import com.app.chuushajou.models.Parking;
import com.app.chuushajou.services.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parkings")
public class ParkingCtrl {


    private final ParkingService parkingService;

    @GetMapping("")
    public ResponseEntity<?> getParkings(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "limit", defaultValue = "10") int limit){
        PageRequest pageRequest = PageRequest.of(page-1, limit);
        Page<ParkingDTO> parkingPage = parkingService.getParkings(pageRequest);
        return ResponseEntity.ok(
                PageInfo.of(parkingPage, page, limit)
        );
    }

    @PostMapping("/{parkingId}/add_vehicle/{vehicleId}")
    public ResponseEntity<?> addVehicleToParking(@PathVariable Long parkingId, @PathVariable Long vehicleId) {
        try {
            Integer slotRemain = parkingService.addVehicleToParking(parkingId, vehicleId);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", "Vehicle added to parking, slots remain: " + slotRemain)
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
            Integer slotRemain = parkingService.removeVehicleFromParking(parkingId, vehicleId);

            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", "Vehicle remove from parking, slots remain: " + slotRemain)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createParking(@RequestBody ParkingDTO parkingDTO){
        try {
            ParkingDTO newParking = parkingService.createParking(parkingDTO);
            return ResponseEntity.ok(
                    ResMap.of("status", "success", "data", newParking)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateParking(@RequestBody ParkingDTO parkingDTO, @PathVariable Long id){
        try {
            ParkingDTO updatedParking = parkingService.updateParking(parkingDTO, id);
            return ResponseEntity.ok(
                    ResMap.of("status", "success", "data", updatedParking)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParking(@PathVariable Long id){
        try {
            ParkingDTO removedParking = parkingService.deleteParking(id);
            return ResponseEntity.ok(
                    ResMap.of("status", "success", "message", "Parking removed successfully", "data", removedParking)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }
}
