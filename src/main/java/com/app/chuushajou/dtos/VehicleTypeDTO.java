package com.app.chuushajou.dtos;


import com.app.chuushajou.models.VehicleType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "title",
        "cost"
})
public class VehicleTypeDTO {
    @JsonPropertyOrder("id")
    private long id;

    private String title;

    public static VehicleTypeDTO getVehicleTypeFromModel(VehicleType vehicleType){
        return new VehicleTypeDTO(
                vehicleType.getId(),
                vehicleType.getTitle()
        );
    }
}
