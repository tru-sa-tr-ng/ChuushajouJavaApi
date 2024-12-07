package com.app.chuushajou.dtos;


import com.app.chuushajou.models.Ticket;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "vehicle_id",
        "issue_date",
        "total"
})
public class TicketDTO {
    @JsonProperty("id")
    private Long ticketId;

    @JsonProperty("vehicle_id")
    private Long vehicleId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @JsonProperty("issue_date")
    private LocalDate issueDate;


    private Long total;

    public TicketDTO(Long vehicleId) {
    }

    public static TicketDTO getTicketFromModel(Ticket ticket){
        return new TicketDTO(
                ticket.getId(),
                ticket.getVehicle().getId(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt(),
                ticket.getIssueDate(),
                ticket.getTotal()
        );
    }

}
