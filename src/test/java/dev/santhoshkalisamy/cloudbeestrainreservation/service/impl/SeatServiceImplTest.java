package dev.santhoshkalisamy.cloudbeestrainreservation.service.impl;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Seat;
import dev.santhoshkalisamy.cloudbeestrainreservation.repository.SeatRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.Assert;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SeatServiceImplTest {

    @InjectMocks
    private SeatServiceImpl seatService;
    @Mock
    private SeatRepository seatRepository;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindUnAllocatedSeat() {
        Seat seat = new Seat();
        seat.setSeatNumber(10);
        Mockito.when(seatRepository.findSeatsByAllocatedIsFalseOrderBySection_NameAscSeatNumberAsc()).thenReturn(List.of(seat));
        Assert.notNull(seatService.findUnAllocatedSeat(), "Response Cannot be null");
        Assert.isTrue(seatService.findUnAllocatedSeat().getSeatNumber() == 10, "Seat number must be 10");
    }

    @Test
    public void testFindUnAllocatedSeatNullEmptyList() {
        Assert.isNull(seatService.findUnAllocatedSeat(), "Response Cannot be null");
    }

    @Test
    public void testAllocateSeat() {
        Seat seat = new Seat();
        seat.setSeatNumber(10);
        Mockito.when(seatRepository.save(seat)).thenReturn(seat);
        Assert.notNull(seatService.allocateSeat(seat), "Response Cannot be null");
        Assert.isTrue(seatService.allocateSeat(seat).isAllocated(), "ALlocated status should be true");

    }

}
