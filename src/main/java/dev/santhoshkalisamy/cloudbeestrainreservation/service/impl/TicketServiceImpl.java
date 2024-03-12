package dev.santhoshkalisamy.cloudbeestrainreservation.service.impl;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.*;
import dev.santhoshkalisamy.cloudbeestrainreservation.exception.SeatNotAvailableException;
import dev.santhoshkalisamy.cloudbeestrainreservation.exception.TicketNotFoundException;
import dev.santhoshkalisamy.cloudbeestrainreservation.exception.TrainFullException;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.ChangeSeatRequest;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.TicketPurchaseReceipt;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.TicketPurchaseRequest;
import dev.santhoshkalisamy.cloudbeestrainreservation.repository.TicketRepository;
import dev.santhoshkalisamy.cloudbeestrainreservation.service.SeatService;
import dev.santhoshkalisamy.cloudbeestrainreservation.service.TicketService;
import dev.santhoshkalisamy.cloudbeestrainreservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    private SeatService seatService;

    private UserService userService;

    @Autowired

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Autowired
    public void setSeatService(SeatService seatService) {
        this.seatService = seatService;
    }

    @Override
    public TicketPurchaseReceipt purchaseTicket(TicketPurchaseRequest ticketPurchaseRequest) throws TrainFullException {
        UserDetails userDetails = userService.getUserByEmailId(ticketPurchaseRequest.userDetails().getEmail());
        if(userDetails == null) {
            userDetails = addUser(ticketPurchaseRequest);
        }
        Seat seat = seatService.findUnAllocatedSeat();
        if(seat != null) {
            seat = seatService.allocateSeat(seat);
            Ticket ticket = generateTicket(ticketPurchaseRequest, seat, userDetails);
            return generateReceipt(ticket);
        } else {
            throw new TrainFullException();
        }
    }

    @Override
    public TicketPurchaseReceipt getTicket(int id) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket == null) {
            throw new TicketNotFoundException();
        }
        return generateReceipt(ticket);
    }

    @Override
    public boolean deleteTicket(int ticketId) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        if(ticket != null) {
            ticketRepository.delete(ticket);
            seatService.unAllocateSeat(ticket.getSeat());
            return true;
        }
        throw new TicketNotFoundException();
    }

    @Override
    public TicketPurchaseReceipt changeSeat(int ticketId, ChangeSeatRequest changeSeatRequest) throws TicketNotFoundException, SeatNotAvailableException {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        if(ticket != null) {
            Seat seat = seatService.findUnAllocatedSeatBySeatNumberAndSectionName(changeSeatRequest.seatNo(), changeSeatRequest.section());
            if(seat != null) {
                // Un allocate the previous seat
                seatService.unAllocateSeat(ticket.getSeat());

                //Allocate new seat
                seat.setTicket(ticket);
                seatService.allocateSeat(seat);

                //update ticket
                ticket.setSeat(seat);
                ticketRepository.save(ticket);
                return generateReceipt(ticket);
            } else {
                throw new SeatNotAvailableException();
            }
        }
        throw new TicketNotFoundException();
    }

    private UserDetails addUser(TicketPurchaseRequest ticketPurchaseRequest) {
        UserDetails userDetails;
        userDetails = new UserDetails();
        userDetails.setEmail(ticketPurchaseRequest.userDetails().getEmail());
        userDetails.setFirstName(ticketPurchaseRequest.userDetails().getFirstName());
        userDetails.setLastName(ticketPurchaseRequest.userDetails().getLastName());
        userDetails = userService.addUser(userDetails);
        return userDetails;
    }

    private Ticket generateTicket(TicketPurchaseRequest ticketPurchaseRequest, Seat seat, UserDetails userDetails) {
        Ticket ticket = new Ticket();
        ticket.setSeat(seat);
        ticket.setUserDetails(userDetails);
        ticket.setSource(ticketPurchaseRequest.from());
        ticket.setDestination(ticketPurchaseRequest.to());
        ticket.setPrice(5.0);
        ticket = ticketRepository.save(ticket);
        return ticket;
    }

    private static TicketPurchaseReceipt generateReceipt(Ticket ticket) {
        return new TicketPurchaseReceipt(ticket.getId(),
                ticket.getSource(),
                ticket.getDestination(),
                ticket.getUserDetails(), ticket.getPrice(),
                ticket.getSeat().getSeatNumber(),
                ticket.getSeat().getSection().getName());
    }
}
