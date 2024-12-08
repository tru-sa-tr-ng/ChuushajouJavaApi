package com.app.chuushajou.dtos;

import com.app.chuushajou.models.Parking;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "parking_name",
        "location"
})
public class ParkingDTO {
    @JsonProperty
    private long id;

    @JsonProperty("parking_name")
    private String name;

    private String location;

    private Integer maxSlot;

    private Integer used;
    private Integer avaiableSlot;


    public static ParkingDTO getParkingFromModel(Parking parking){
        return new ParkingDTO(
                parking.getId(),
                parking.getName(),
                parking.getLocation(),
                parking.getMaxSlot(),
                parking.getVehicles().size(),
                parking.getAvaiableSlot()
        );
    }

}
