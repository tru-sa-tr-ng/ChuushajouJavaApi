package com.app.chuushajou.responses;

import com.app.chuushajou.models.Vehicle;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class VehicleResponse {
    @JsonProperty("customer_id")
    private int customerId;
    @JsonProperty("type_id")
    private int typeId;

    private String license;
    private String color;
    private String img;



    public static VehicleResponse getVehicleFromModel(Vehicle vehicle){
        return new VehicleResponse(
                Math.toIntExact(vehicle.getCustomer().getId()),
                Math.toIntExact(vehicle.getType().getId()),
                vehicle.getLicense(),
                vehicle.getColor(),
                vehicle.getImg()
        );
    }
}
