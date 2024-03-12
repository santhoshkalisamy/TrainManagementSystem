package dev.santhoshkalisamy.cloudbeestrainreservation.service;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Seat;
import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Section;
import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Train;

public interface SeatService {
    public Seat findUnAllocatedSeat(Train train);
    public Seat findUnAllocatedSeat(Section section);
    public Seat findUnAllocatedSeat();

    public Seat findUnAllocatedSeatBySeatNumberAndSectionName(int seatNumber, String sectionName);
    public void unAllocateSeat(Seat seat);
    public Seat allocateSeat(Seat seat);
    public Seat findAllSeats(Train train);
    public Seat findAllSeats(Section section);
    public Seat findAllSeats();
}
