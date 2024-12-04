package com.app.chuushajou.dtos;

import com.app.chuushajou.models.Vehicle;
import com.app.chuushajou.models.VehicleEntryExitHistory;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "customer_id",
        "type_id",
        "license",
        "color",
        "img",
        "entryTime",
        "createdAt",
        "updatedAt"
})
public class VehicleDTO {
    @JsonProperty("id")
    private long vehicleId;
    @JsonProperty("customer_id")
    private long customerId;
    @JsonProperty("type_id")
    private long typeId;

    private String license;
    private String color;
    private String img;


    private List<VehicleEntryExitHistory> vehicleEntryExitHistories;
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
                vehicle.getHistories(),
                vehicle.getCreatedAt(),
                vehicle.getUpdatedAt()
        );
    }
}
