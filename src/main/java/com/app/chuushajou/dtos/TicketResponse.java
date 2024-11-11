package com.app.chuushajou.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class TicketResponse {
    @JsonProperty("vehicle_id")
    private Long vehicleId;
    private Date issue_date;
    private Date expiry_date;
    private Integer total;




}
