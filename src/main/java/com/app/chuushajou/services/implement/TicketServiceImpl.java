package com.app.chuushajou.services.implement;


import com.app.chuushajou.dtos.TicketDTO;
import com.app.chuushajou.dtos.VehicleDTO;
import com.app.chuushajou.models.Customer;
import com.app.chuushajou.models.Ticket;
import com.app.chuushajou.models.Vehicle;
import com.app.chuushajou.repositories.TicketRepository;
import com.app.chuushajou.repositories.VehicleRepository;
import com.app.chuushajou.services.TicketService;
import com.app.chuushajou.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleService vehicleService;

    @Override
    public Page<TicketDTO> getTickets(PageRequest pageRequest){
        return ticketRepository.findAll(pageRequest).map(TicketDTO::getTicketFromModel);
    }

    @Override
    public TicketDTO getTicketById(long id) throws Exception {
        return TicketDTO.getTicketFromModel(ticketRepository.findById(id).orElseThrow());
    }

    @Override
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();

        ticket.setVehicle(vehicleRepository.getReferenceById(ticketDTO.getVehicleId()));
        ticket.setIssueDate(ticketDTO.getIssueDate());
        ticket.setTotal(ticketDTO.getTotal());

        return TicketDTO.getTicketFromModel(ticketRepository.save(ticket));
    }


    @Override
    public TicketDTO updateTicket(TicketDTO ticketDTO, long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();

        if (ticketDTO.getVehicleId() != null)
            ticket.setVehicle(vehicleRepository.getReferenceById(ticketDTO.getVehicleId()));

        if (ticketDTO.getIssueDate() != null)
            ticket.setIssueDate(ticketDTO.getIssueDate());

        if (ticketDTO.getTotal() != null)
            ticket.setTotal(ticketDTO.getTotal());

        return TicketDTO.getTicketFromModel(ticketRepository.save(ticket));
    }

    @Override
    public TicketDTO deleteTicket(long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();

        ticketRepository.delete(ticket);

        return TicketDTO.getTicketFromModel(ticket);
    }

    @Override
    public Object returnTicket(long id){
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        ticket.setIssueDate();


        Customer customer = ticket.getVehicle().getCustomer();

        if (customer == null || customer.getId() == 0 ) {
            return "Please pay with tiền mặt";
        } else {
            Long remain = customer.getRemain();
            if (remain < ticket.getTotal()) throw new RuntimeException("Customer has not enough money to pay for this ticket: " + customer.getRemain());
            else ticket.getVehicle().getCustomer().setRemain(remain - ticket.getTotal());
            return ticket;
        }
    }


    @Override
    public TicketDTO checkAndCreateTicket(VehicleDTO vehicleDTO) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findByLicense(vehicleDTO.getLicense());

        VehicleDTO newVehicleDTO;

        if (vehicleOpt.isEmpty()) newVehicleDTO = vehicleService.createVehicle(vehicleDTO);
        else newVehicleDTO = vehicleService.updateVehicle(vehicleDTO, vehicleOpt.get().getId());

        TicketDTO ticketDTO = new TicketDTO(newVehicleDTO.getVehicleId());
        return createTicket(ticketDTO);

    }

}

