package com.app.chuushajou.services;

import com.app.chuushajou.dtos.TicketDTO;
import com.app.chuushajou.dtos.VehicleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public interface TicketService {
    Page<TicketDTO> getTickets(PageRequest pageRequest);

    TicketDTO getTicketById(long id) throws Exception;

    TicketDTO createTicket(TicketDTO ticketDTO);

    TicketDTO updateTicket(TicketDTO ticketDTO, long id);

    TicketDTO deleteTicket(long id);

    Object returnTicket(long id);

    TicketDTO checkAndCreateTicket(VehicleDTO vehicleDTO);

}
