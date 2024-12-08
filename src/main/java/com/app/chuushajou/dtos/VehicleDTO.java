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
        "id",
        "customer_id",
        "type_id",
        "parking_id",
        "license",
        "color",
        "img",
        "createdAt",
        "updatedAt"
})
public class VehicleDTO {
    @JsonProperty("id")
    private Long vehicleId;
    @JsonProperty("customer_id")
    private Long customerId;
    @JsonProperty("type_id")
    private Long typeId;

    @JsonProperty("parking_id")
    private Long parkingId;

    private String license;
    private String color;
    private String img;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    public static VehicleDTO getVehicleFromModel(Vehicle vehicle){
        return new VehicleDTO(
                vehicle.getId(),
                (vehicle.getCustomer() != null) ? vehicle.getCustomer().getId() : null,
                (vehicle.getType() != null) ? vehicle.getType().getId() : null,
                (vehicle.getParking() != null) ? vehicle.getParking().getId() : null,
                vehicle.getLicense(),
                vehicle.getColor(),
                vehicle.getImg(),
                vehicle.getCreatedAt(),
                vehicle.getUpdatedAt()
        );
    }

    public VehicleDTO() {
    }
}
