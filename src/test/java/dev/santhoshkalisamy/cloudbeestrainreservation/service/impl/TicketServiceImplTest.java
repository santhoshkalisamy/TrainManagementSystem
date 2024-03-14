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
import dev.santhoshkalisamy.cloudbeestrainreservation.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private SeatService seatService;

    @Mock
    private UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void purchaseTicketReturnsReceipt() throws TrainFullException {
        TicketPurchaseRequest request = new TicketPurchaseRequest("LONDON", "FRANCE", new UserDetails());
        UserDetails userDetails = new UserDetails();
        Seat seat = new Seat();
        Ticket ticket = new Ticket();
        Section section = new Section();
        section.setName("A");
        seat.setSection(section);
        ticket.setSeat(seat);
        ticket.setUserDetails(userDetails);

        when(userService.getUserByEmailId(any())).thenReturn(null);
        when(userService.addUser(any())).thenReturn(userDetails);
        when(seatService.findUnAllocatedSeat()).thenReturn(seat);
        when(ticketRepository.save(any())).thenReturn(ticket);

        TicketPurchaseReceipt result = ticketService.purchaseTicket(request);

        assertNotNull(result);
        assertEquals(userDetails, result.userDetails());
    }

    @Test(expected = TrainFullException.class)
    public void purchaseTicketThrowsExceptionWhenNoSeats() throws TrainFullException {
        TicketPurchaseRequest request = new TicketPurchaseRequest("LONDON", "FRANCE", new UserDetails());
        UserDetails userDetails = new UserDetails();

        when(userService.getUserByEmailId(any())).thenReturn(null);
        when(userService.addUser(any())).thenReturn(userDetails);
        when(seatService.findUnAllocatedSeat()).thenReturn(null);

        ticketService.purchaseTicket(request);
    }

    @Test
    public void getTicketReturnsTicket() throws TicketNotFoundException {
        int id = 1;
        Seat seat = new Seat();
        Ticket ticket = new Ticket();
        Section section = new Section();
        section.setName("A");
        seat.setSection(section);
        ticket.setSeat(seat);

        when(ticketRepository.findById(id)).thenReturn(Optional.of(ticket));

        TicketPurchaseReceipt result = ticketService.getTicket(id);

        assertNotNull(result);
    }

    @Test(expected = TicketNotFoundException.class)
    public void getTicketThrowsExceptionWhenNotFound() throws TicketNotFoundException {
        int id = 1;

        when(ticketRepository.findById(id)).thenReturn(Optional.empty());

        ticketService.getTicket(id);
    }

    @Test
    public void deleteTicketReturnsTrue() throws TicketNotFoundException {
        int id = 1;
        Ticket ticket = new Ticket();
        Seat seat = new Seat();
        ticket.setSeat(seat);

        when(ticketRepository.findById(id)).thenReturn(Optional.of(ticket));

        boolean result = ticketService.deleteTicket(id);

        assertTrue(result);
    }

    @Test(expected = TicketNotFoundException.class)
    public void deleteTicketThrowsExceptionWhenNotFound() throws TicketNotFoundException {
        int id = 1;

        when(ticketRepository.findById(id)).thenReturn(Optional.empty());

        ticketService.deleteTicket(id);
    }

    @Test
    public void changeSeatReturnsUpdatedTicket() throws TicketNotFoundException, SeatNotAvailableException {
        int id = 1;
        ChangeSeatRequest request = new ChangeSeatRequest("A", 1);
        Ticket ticket = new Ticket();
        Seat seat = new Seat();
        Section section = new Section();
        section.setName("A");
        seat.setSection(section);
        ticket.setSeat(seat);

        when(ticketRepository.findById(id)).thenReturn(Optional.of(ticket));
        when(seatService.findUnAllocatedSeatBySeatNumberAndSectionName(any(), any())).thenReturn(seat);
        when(ticketRepository.save(any())).thenReturn(ticket);

        TicketPurchaseReceipt result = ticketService.changeSeat(id, request);

        assertNotNull(result);
    }

    @Test(expected = TicketNotFoundException.class)
    public void changeSeatThrowsExceptionWhenTicketNotFound() throws TicketNotFoundException, SeatNotAvailableException {
        int id = 1;
        ChangeSeatRequest request = new ChangeSeatRequest("A", 1);

        when(ticketRepository.findById(id)).thenReturn(Optional.empty());

        ticketService.changeSeat(id, request);
    }

    @Test(expected = SeatNotAvailableException.class)
    public void changeSeatThrowsExceptionWhenSeatNotAvailable() throws TicketNotFoundException, SeatNotAvailableException {
        int id = 1;
        ChangeSeatRequest request = new ChangeSeatRequest("A", 1);
        Ticket ticket = new Ticket();

        when(ticketRepository.findById(id)).thenReturn(Optional.of(ticket));
        when(seatService.findUnAllocatedSeatBySeatNumberAndSectionName(any(), any())).thenReturn(null);

        ticketService.changeSeat(id, request);
    }
}
