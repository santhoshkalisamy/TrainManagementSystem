package dev.santhoshkalisamy.cloudbeestrainreservation.controller;

import dev.santhoshkalisamy.cloudbeestrainreservation.exception.SeatNotAvailableException;
import dev.santhoshkalisamy.cloudbeestrainreservation.exception.TicketNotFoundException;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.ChangeSeatRequest;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.TicketPurchaseReceipt;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.TicketPurchaseRequest;
import dev.santhoshkalisamy.cloudbeestrainreservation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    private TicketService ticketService;

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @PostMapping("/purchase")
    public ResponseEntity<TicketPurchaseReceipt> purchaseTicket(@RequestBody TicketPurchaseRequest ticketPurchaseRequest) throws Exception {
        return ResponseEntity.status(201).body(ticketService.purchaseTicket(ticketPurchaseRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketPurchaseReceipt> purchaseTicket(@PathVariable("id") int ticketId) throws Exception {
        return ResponseEntity.status(200).body(ticketService.getTicket(ticketId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable("id") int ticketId) throws TicketNotFoundException {
        return ResponseEntity.status(200).body(ticketService.deleteTicket(ticketId) ? "Success" : "Failed");
    }

    @PatchMapping("/{id}/seat")
    public ResponseEntity<TicketPurchaseReceipt> modifyTicket(@RequestBody ChangeSeatRequest changeSeatRequest, @PathVariable("id") int ticketId) throws TicketNotFoundException, SeatNotAvailableException {
        return ResponseEntity.status(200).body(ticketService.changeSeat(ticketId, changeSeatRequest));
    }

}
