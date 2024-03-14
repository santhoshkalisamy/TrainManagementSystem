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

/**
 * This is the controller class for the Ticket entity.
 * It handles HTTP requests related to tickets.
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {
    private TicketService ticketService;

    /**
     * This method is used to inject the TicketService dependency.
     * @param ticketService The service class for Ticket entity.
     */
    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * This method handles the POST request to purchase a ticket.
     * @param ticketPurchaseRequest The request body containing the details of the ticket to be purchased.
     * @return A ResponseEntity containing the TicketPurchaseReceipt and the HTTP status.
     * @throws Exception If there is an error during the ticket purchase process.
     */
    @PostMapping("/purchase")
    public ResponseEntity<TicketPurchaseReceipt> purchaseTicket(@RequestBody TicketPurchaseRequest ticketPurchaseRequest) throws Exception {
        return ResponseEntity.status(201).body(ticketService.purchaseTicket(ticketPurchaseRequest));
    }

    /**
     * This method handles the GET request to fetch a ticket by its ID.
     * @param ticketId The ID of the ticket to be fetched.
     * @return A ResponseEntity containing the TicketPurchaseReceipt and the HTTP status.
     * @throws Exception If there is an error during the ticket retrieval process.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TicketPurchaseReceipt> purchaseTicket(@PathVariable("id") int ticketId) throws Exception {
        return ResponseEntity.status(200).body(ticketService.getTicket(ticketId));
    }

    /**
     * This method handles the DELETE request to delete a ticket by its ID.
     * @param ticketId The ID of the ticket to be deleted.
     * @return A ResponseEntity containing a success or failure message and the HTTP status.
     * @throws TicketNotFoundException If the ticket with the given ID is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable("id") int ticketId) throws TicketNotFoundException {
        return ResponseEntity.status(200).body(ticketService.deleteTicket(ticketId) ? "Success" : "Failed");
    }

    /**
     * This method handles the PATCH request to change the seat of a ticket.
     * @param changeSeatRequest The request body containing the details of the new seat.
     * @param ticketId The ID of the ticket for which the seat is to be changed.
     * @return A ResponseEntity containing the updated TicketPurchaseReceipt and the HTTP status.
     * @throws TicketNotFoundException If the ticket with the given ID is not found.
     * @throws SeatNotAvailableException If the requested seat is not available.
     */
    @PatchMapping("/{id}/seat")
    public ResponseEntity<TicketPurchaseReceipt> modifyTicket(@RequestBody ChangeSeatRequest changeSeatRequest, @PathVariable("id") int ticketId) throws TicketNotFoundException, SeatNotAvailableException {
        return ResponseEntity.status(200).body(ticketService.changeSeat(ticketId, changeSeatRequest));
    }

}
