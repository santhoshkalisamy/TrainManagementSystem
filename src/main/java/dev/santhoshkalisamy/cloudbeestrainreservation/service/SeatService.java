package dev.santhoshkalisamy.cloudbeestrainreservation.service;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Seat;

/**
 * This is the service interface for the Seat entity.
 * It declares methods for seat-related operations.
 */
public interface SeatService {

    /**
     * This method is used to find an unallocated seat.
     * @return An unallocated Seat entity.
     */
    public Seat findUnAllocatedSeat();

    /**
     * This method is used to find an unallocated seat by its number and the name of its section.
     * @param seatNumber The number of the seat.
     * @param sectionName The name of the section.
     * @return An unallocated Seat entity that matches the given seat number and section name.
     */
    public Seat findUnAllocatedSeatBySeatNumberAndSectionName(Integer seatNumber, String sectionName);

    /**
     * This method is used to unallocate a seat.
     * @param seat The Seat entity to be unallocated.
     */
    public void unAllocateSeat(Seat seat);

    /**
     * This method is used to allocate a seat.
     * @param seat The Seat entity to be allocated.
     * @return The allocated Seat entity.
     */
    public Seat allocateSeat(Seat seat);
}
