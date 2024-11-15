package com.app.chuushajou.dtos;


import com.app.chuushajou.models.Ticket;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "vehicle_id",
        "ticket_type",
        "issue_date",
        "expiry_date",
        "total"
})
public class TicketDTO {
    @JsonProperty("id")
    private long ticketId;

    @JsonProperty("vehicle_id")
    private long vehicleId;

    private String ticket_type;

    private Date issue_date;

    private Date expiry_date;

    private Integer total;

    public static TicketDTO getTicketFromModel(Ticket ticket){
        return new TicketDTO(
                ticket.getId(),
                ticket.getVehicle().getId(),
                ticket.getTicket_type(),
                ticket.getIssue_date(),
                ticket.getExpiry_date(),
                ticket.getTotal()
        );
    }

}
