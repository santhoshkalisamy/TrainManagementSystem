package dev.santhoshkalisamy.cloudbeestrainreservation.service;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Ticket;
import dev.santhoshkalisamy.cloudbeestrainreservation.exception.SeatNotAvailableException;
import dev.santhoshkalisamy.cloudbeestrainreservation.exception.TicketNotFoundException;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.ChangeSeatRequest;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.TicketPurchaseReceipt;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.TicketPurchaseRequest;


public interface TicketService {
    public TicketPurchaseReceipt purchaseTicket(TicketPurchaseRequest ticketPurchaseRequest) throws Exception;
    public TicketPurchaseReceipt getTicket(int id) throws TicketNotFoundException;
    public boolean deleteTicket(int ticketId) throws TicketNotFoundException;
    public TicketPurchaseReceipt changeSeat(int ticketId, ChangeSeatRequest changeSeatRequest) throws TicketNotFoundException, SeatNotAvailableException;
}
