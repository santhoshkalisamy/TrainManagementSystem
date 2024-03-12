package dev.santhoshkalisamy.cloudbeestrainreservation.service.impl;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Seat;
import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Section;
import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Ticket;
import dev.santhoshkalisamy.cloudbeestrainreservation.entity.UserDetails;
import dev.santhoshkalisamy.cloudbeestrainreservation.exception.TrainFullException;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.TicketPurchaseReceipt;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.TicketPurchaseRequest;
import dev.santhoshkalisamy.cloudbeestrainreservation.repository.TicketRepository;
import dev.santhoshkalisamy.cloudbeestrainreservation.service.SeatService;
import dev.santhoshkalisamy.cloudbeestrainreservation.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

    @InjectMocks
    TicketServiceImpl ticketService;

    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private SeatService seatService;
    @Mock
    private UserService userService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPurchaseTicket() throws TrainFullException {
        TicketPurchaseRequest ticketPurchaseRequest = generateMocks();
        TicketPurchaseReceipt ticketPurchaseReceipt = ticketService.purchaseTicket(ticketPurchaseRequest);
        Assert.assertNotNull(ticketPurchaseReceipt);
        Assert.assertEquals(10.0, ticketPurchaseReceipt.price(), 0.0);
    }

    private TicketPurchaseRequest generateMocks() {
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName("Santhosh");
        userDetails.setLastName("K");
        userDetails.setEmail("san@gmail.com");
        userDetails.setId(1001);
        Section section = new Section();
        Seat seat = new Seat();
        seat.setSection(section);
        seat.setSeatNumber(10);
        Ticket ticket = new Ticket();
        ticket.setSeat(seat);
        ticket.setPrice(10.0);
        ticket.setUserDetails(userDetails);
        ticket.setSource("LONDON");
        ticket.setDestination("FRANCE");
        TicketPurchaseRequest ticketPurchaseRequest = new TicketPurchaseRequest("LONDON", "FRANCE", userDetails);
        Mockito.when(userService.getUserByEmailId("san@gmail.com")).thenReturn(null);
        Mockito.when(userService.addUser(Mockito.any())).thenReturn(userDetails);
        Mockito.when(seatService.findUnAllocatedSeat()).thenReturn(seat);
        Mockito.when(ticketRepository.save(Mockito.any())).thenReturn(ticket);
        return ticketPurchaseRequest;
    }

    @Test(expected = TrainFullException.class)
    public void testPurchaseTicketException() throws TrainFullException {
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName("Santhosh");
        userDetails.setLastName("K");
        userDetails.setEmail("san@gmail.com");
        userDetails.setId(1001);
        Section section = new Section();
        Seat seat = new Seat();
        seat.setSection(section);
        seat.setSeatNumber(10);
        Ticket ticket = new Ticket();
        ticket.setSeat(seat);
        ticket.setPrice(10.0);
        ticket.setUserDetails(userDetails);
        ticket.setSource("LONDON");
        ticket.setDestination("FRANCE");
        TicketPurchaseRequest ticketPurchaseRequest = new TicketPurchaseRequest("LONDON", "FRANCE", userDetails);
        Mockito.when(userService.getUserByEmailId("san@gmail.com")).thenReturn(null);
        Mockito.when(userService.addUser(Mockito.any())).thenReturn(userDetails);
        Mockito.when(seatService.findUnAllocatedSeat()).thenReturn(null);
        TicketPurchaseReceipt ticketPurchaseReceipt = ticketService.purchaseTicket(ticketPurchaseRequest);
    }
}
