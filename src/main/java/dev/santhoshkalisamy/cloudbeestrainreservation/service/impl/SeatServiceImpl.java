package dev.santhoshkalisamy.cloudbeestrainreservation.service.impl;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Seat;
import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Section;
import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Train;
import dev.santhoshkalisamy.cloudbeestrainreservation.repository.SeatRepository;
import dev.santhoshkalisamy.cloudbeestrainreservation.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    private SeatRepository seatRepository;

    @Autowired
    public void setSeatRepository(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public Seat findUnAllocatedSeat(Train train) {
        return null;
    }

    @Override
    public Seat findUnAllocatedSeat(Section section) {
        return null;
    }

    @Override
    public Seat findUnAllocatedSeat() {
        List<Seat> freeSeats = seatRepository.findSeatsByAllocatedIsFalseOrderBySeatNumberAscSectionAsc();
        if(!freeSeats.isEmpty()) {
            return freeSeats.get(0);
        }
        return null;
    }

    @Override
    public Seat findUnAllocatedSeatBySeatNumberAndSectionName(int seatNumber, String sectionName) {
        return seatRepository.findSeatsBySeatNumberAndAllocatedIsFalseAndSection_Name(seatNumber, sectionName);
    }

    @Override
    public void unAllocateSeat(Seat seat) {
        seat.setAllocated(false);
        seatRepository.save(seat);
    }

    @Override
    public Seat allocateSeat(Seat seat) {
        seat.setAllocated(true);
        return seatRepository.save(seat);
    }

    @Override
    public Seat findAllSeats(Train train) {
        return null;
    }

    @Override
    public Seat findAllSeats(Section section) {
        return null;
    }

    @Override
    public Seat findAllSeats() {
        return null;
    }
}
