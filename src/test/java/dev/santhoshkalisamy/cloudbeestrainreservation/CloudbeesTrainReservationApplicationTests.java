package dev.santhoshkalisamy.cloudbeestrainreservation;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.UserDetails;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.ChangeSeatRequest;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.Error;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.TicketPurchaseReceipt;
import dev.santhoshkalisamy.cloudbeestrainreservation.model.TicketPurchaseRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudbeesTrainReservationApplicationTests {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldPurchaseATicket() throws Exception {
        TicketPurchaseReceipt ticketPurchaseReceipt = getTicketPurchaseReceipt();
        performAssertions(ticketPurchaseReceipt.id(), ticketPurchaseReceipt);
    }

    @Test
    public void shouldPurchaseATicketAndGetDetails() throws Exception {
        TicketPurchaseReceipt ticketPurchaseReceipt = getTicketPurchaseReceipt();
        TicketPurchaseReceipt getTicketPurchaseReceipt = this.restTemplate.getForObject("http://localhost:" + port + "/ticket/"+ticketPurchaseReceipt.id(), TicketPurchaseReceipt.class);
        performAssertions(ticketPurchaseReceipt.id(), getTicketPurchaseReceipt);
    }

    @Test
    public void shouldDeleteTicket() throws Exception {
        TicketPurchaseReceipt ticketPurchaseReceipt1 = getTicketPurchaseReceipt();
        TicketPurchaseReceipt ticketPurchaseReceipt2 = getTicketPurchaseReceipt();
        TicketPurchaseReceipt getTicketPurchaseReceipt = this.restTemplate.getForObject("http://localhost:" + port + "/ticket/"+ticketPurchaseReceipt2.id(), TicketPurchaseReceipt.class);
        performAssertions(ticketPurchaseReceipt2.id(), getTicketPurchaseReceipt);
        this.restTemplate.delete("http://localhost:" + port + "/ticket/"+ticketPurchaseReceipt2.id());
        Error error = this.restTemplate.getForObject("http://localhost:" + port + "/ticket/"+ticketPurchaseReceipt2.id(), Error.class);
        Assertions.assertNotNull(error);
        Assertions.assertEquals( 102,error.getErrorCode());
        Assertions.assertEquals( "Ticket details not found. Please check the id", error.getDetails());
    }

    @Test
    public void shouldUpdateSeat() throws Exception {
        ChangeSeatRequest changeSeatRequest = new ChangeSeatRequest("B", 4);
        TicketPurchaseReceipt ticketPurchaseReceipt2 = getTicketPurchaseReceipt();
        TicketPurchaseReceipt getTicketPurchaseReceipt = this.restTemplate.getForObject("http://localhost:" + port + "/ticket/"+ticketPurchaseReceipt2.id(), TicketPurchaseReceipt.class);
        performAssertions(ticketPurchaseReceipt2.id(), getTicketPurchaseReceipt);
        TicketPurchaseReceipt updateTicketPurchaseReceipt = this.restTemplate.patchForObject("http://localhost:" + port + "/ticket/"+ticketPurchaseReceipt2.id()+"/seat",changeSeatRequest, TicketPurchaseReceipt.class);
        Assertions.assertEquals("B", updateTicketPurchaseReceipt.section());
        Assertions.assertEquals(4, updateTicketPurchaseReceipt.seatNo());
    }

    private static void performAssertions(int ticketPurchaseReceipt2, TicketPurchaseReceipt getTicketPurchaseReceipt) {
        Assertions.assertEquals(ticketPurchaseReceipt2, getTicketPurchaseReceipt.id());
        Assertions.assertEquals("LONDON", getTicketPurchaseReceipt.from());
        Assertions.assertEquals("FRANCE", getTicketPurchaseReceipt.to());
        Assertions.assertEquals("san@gmail.com", getTicketPurchaseReceipt.userDetails().getEmail());
        Assertions.assertEquals(5.0, getTicketPurchaseReceipt.price());
        Assertions.assertTrue(getTicketPurchaseReceipt.seatNo() > 0);
        Assertions.assertTrue(getTicketPurchaseReceipt.section().equals("A") || getTicketPurchaseReceipt.section().equals("B"));
    }

    private TicketPurchaseReceipt getTicketPurchaseReceipt() {
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName("Santhosh");
        userDetails.setLastName("K");
        userDetails.setEmail("san@gmail.com");
        userDetails.setId(1001);
        TicketPurchaseRequest ticketPurchaseRequest =
                new TicketPurchaseRequest("LONDON", "FRANCE", userDetails);
        return this.restTemplate.postForObject("http://localhost:" + port + "/ticket/purchase", ticketPurchaseRequest, TicketPurchaseReceipt.class);
    }

}
