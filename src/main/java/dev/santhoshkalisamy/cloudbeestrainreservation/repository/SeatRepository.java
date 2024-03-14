package dev.santhoshkalisamy.cloudbeestrainreservation.repository;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    public List<Seat> findSeatsByAllocatedIsFalseOrderBySeatNumber();
    public List<Seat> findSeatsByAllocatedIsFalseOrderBySection_NameAscSeatNumberAsc();
    public Seat findSeatsBySeatNumberAndAllocatedIsFalseAndSection_Name(int seatNumber, String sectionName);
 }
