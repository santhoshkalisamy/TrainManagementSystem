package dev.santhoshkalisamy.cloudbeestrainreservation.repository;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SectionRepository extends JpaRepository<Section, Integer> {
    public Section findSectionByName(String name);
    @Query("SELECT s FROM Section s JOIN FETCH s.seats seat WHERE s.name = :name AND seat.allocated = true")
    public Section findSectionAndAllocatedSeats(String name);

    @Query("SELECT s FROM Section s JOIN FETCH s.seats seat WHERE s.name = :name AND seat.allocated = false ")
    public Section findSectionAndUnAllocatedSeats(String name);
}
