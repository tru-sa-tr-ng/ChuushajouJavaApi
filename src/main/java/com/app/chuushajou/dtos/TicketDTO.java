package com.app.chuushajou.dtos;


import com.app.chuushajou.models.Ticket;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

    private long ticket_type;

    private LocalDateTime expiry_date;

    private LocalDateTime issue_date;


    private Integer total;

    public static TicketDTO getTicketFromModel(Ticket ticket){
        return new TicketDTO(
                ticket.getId(),
                ticket.getVehicle().getId(),
                ticket.getTicketType().getId(),
                ticket.getCreatedAt(),
                ticket.getIssueDate(),
                ticket.getTotal()
        );
    }

}
