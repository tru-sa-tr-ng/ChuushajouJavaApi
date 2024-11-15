package com.app.chuushajou.services;

import com.app.chuushajou.dtos.TicketDTO;
import com.app.chuushajou.models.Ticket;
import com.app.chuushajou.repositories.TicketRepository;
import com.app.chuushajou.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final VehicleRepository vehicleRepository;

    public Page<TicketDTO> getTickets(PageRequest pageRequest){
        return ticketRepository.findAll(pageRequest).map(TicketDTO::getTicketFromModel);
    }

    public TicketDTO getTicketById(long id) throws Exception {
        return TicketDTO.getTicketFromModel(ticketRepository.findById(id).orElseThrow());
    }

    public TicketDTO addTicket(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();

        ticket.setVehicle(vehicleRepository.getReferenceById(ticketDTO.getVehicleId()));
        ticket.setTicket_type(ticketDTO.getTicket_type());
        ticket.setIssue_date(ticketDTO.getIssue_date());
        ticket.setExpiry_date(ticketDTO.getExpiry_date());
        ticket.setTotal(ticketDTO.getTotal());

        return TicketDTO.getTicketFromModel(ticketRepository.save(ticket));
    }

    public TicketDTO updateTicket(TicketDTO ticketDTO, long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();

        if (ticketDTO.getVehicleId() != 0)
            ticket.setVehicle(vehicleRepository.getReferenceById(ticketDTO.getVehicleId()));

        if (ticketDTO.getTicket_type() != null)
            ticket.setTicket_type(ticketDTO.getTicket_type());

        if (ticketDTO.getIssue_date() != null)
            ticket.setIssue_date(ticketDTO.getIssue_date());

        if (ticketDTO.getExpiry_date() != null)
            ticket.setExpiry_date(ticketDTO.getExpiry_date());

        if (ticketDTO.getTotal() != null)
            ticket.setTotal(ticketDTO.getTotal());

        return TicketDTO.getTicketFromModel(ticketRepository.save(ticket));
    }

    public TicketDTO removeTicket(long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();

        ticketRepository.delete(ticket);

        return TicketDTO.getTicketFromModel(ticket);
    }
}
