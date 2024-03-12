package dev.santhoshkalisamy.cloudbeestrainreservation.repository;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    public boolean removeById(int id);
}
