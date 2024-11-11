package com.app.chuushajou.dtos;

import com.app.chuushajou.models.Vehicle;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@JsonPropertyOrder({
        "vehicle_id",
        "customer_id",
        "type_id",
        "license",
        "color",
        "img",
        "createdAt",
        "updatedAt"
})
public class VehicleDTO {
    @JsonProperty("vehicle_id")
    private long vehicleId;
    @JsonProperty("customer_id")
    private long customerId;
    @JsonProperty("type_id")
    private long typeId;

    private String license;
    private String color;
    private String img;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    public static VehicleDTO getVehicleFromModel(Vehicle vehicle){
        return new VehicleDTO(
                vehicle.getId(),
                vehicle.getCustomer().getId(),
                vehicle.getType().getId(),
                vehicle.getLicense(),
                vehicle.getColor(),
                vehicle.getImg(),
                vehicle.getCreatedAt(),
                vehicle.getUpdatedAt()
        );
    }
}
