package com.app.chuushajou.controllers;


import com.app.chuushajou.dtos.TicketDTO;
import com.app.chuushajou.libs.PageInfo;
import com.app.chuushajou.libs.ResMap;
import com.app.chuushajou.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketCtrl {
    private final TicketService ticketService;

    @GetMapping("")
    public ResponseEntity<?> getTickets (
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit)
    {
        PageRequest pageRequest = PageRequest.of(page-1, limit);
        Page<TicketDTO> ticketPage = ticketService.getTickets(pageRequest);
        return ResponseEntity.ok(
                PageInfo.of(ticketPage, page, limit)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable("id") Long ticketId) {
        try {
            TicketDTO ticketDTO = ticketService.getTicketById(ticketId);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", ticketDTO)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTicket(@RequestBody TicketDTO ticketDTO) {
        try {
            TicketDTO newTicketDTO = ticketService.createTicket(ticketDTO);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", newTicketDTO)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable("id") Long ticketId, @RequestBody TicketDTO ticketDTO) {
        try {
            TicketDTO updatedTicketDTO = ticketService.updateTicket(ticketDTO, ticketId);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", updatedTicketDTO)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable("id") Long ticketId) {
        try {
            TicketDTO removedTicketDTO = ticketService.deleteTicket(ticketId);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", removedTicketDTO)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }
}
