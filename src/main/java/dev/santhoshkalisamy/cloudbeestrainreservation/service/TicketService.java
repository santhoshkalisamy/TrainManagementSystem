package dev.santhoshkalisamy.cloudbeestrainreservation.service;

import dev.santhoshkalisamy.cloudbeestrainreservation.exception.SeatNotAvailableException;
import dev.santhoshkalisamy.cloudbeestrainreservation.exception.TicketNotFoundException;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.ChangeSeatRequest;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.TicketPurchaseReceipt;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.TicketPurchaseRequest;

/**
 * This is the service interface for the Ticket entity.
 * It declares methods for ticket-related operations.
 */
public interface TicketService {

    /**
     * This method is used to purchase a ticket.
     * @param ticketPurchaseRequest The request object containing the details of the ticket to be purchased.
     * @return A receipt of the ticket purchase.
     * @throws Exception If an error occurs during the ticket purchase.
     */
    public TicketPurchaseReceipt purchaseTicket(TicketPurchaseRequest ticketPurchaseRequest) throws Exception;

    /**
     * This method is used to get a ticket by its ID.
     * @param id The ID of the ticket.
     * @return The ticket that matches the given ID.
     * @throws TicketNotFoundException If a ticket with the given ID is not found.
     */
    public TicketPurchaseReceipt getTicket(int id) throws TicketNotFoundException;

    /**
     * This method is used to delete a ticket by its ID.
     * @param ticketId The ID of the ticket to be deleted.
     * @return A boolean indicating whether the ticket was successfully deleted.
     * @throws TicketNotFoundException If a ticket with the given ID is not found.
     */
    public boolean deleteTicket(int ticketId) throws TicketNotFoundException;

    /**
     * This method is used to change the seat of a ticket.
     * @param ticketId The ID of the ticket.
     * @param changeSeatRequest The request object containing the details of the seat change.
     * @return A receipt of the ticket with the updated seat.
     * @throws TicketNotFoundException If a ticket with the given ID is not found.
     * @throws SeatNotAvailableException If the requested seat is not available.
     */
    public TicketPurchaseReceipt changeSeat(int ticketId, ChangeSeatRequest changeSeatRequest) throws TicketNotFoundException, SeatNotAvailableException;
}
