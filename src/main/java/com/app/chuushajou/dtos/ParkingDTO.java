package com.app.chuushajou.dtos;

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

    @JsonProperty("location")
    private String location;

    public static ParkingDTO getParkingFromModel(com.app.chuushajou.models.Parking parking){
        return new ParkingDTO(
                parking.getId(),
                parking.getName(),
                parking.getLocation()
        );
    }

}
