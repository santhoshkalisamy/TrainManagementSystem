package dev.santhoshkalisamy.cloudbeestrainreservation.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Seat {
    @Column
    int seatNumber;
    @Column
    boolean allocated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    @JsonIgnore
    Section section;
    @OneToOne(mappedBy = "seat")
    @JsonProperty("userDetails")
    Ticket ticket;

    Integer ticketNumber;

    public Integer getTicketNumber() {
        if(ticket == null) {
            return null;
        }
        return ticket.getId();
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public UserDetails getTicket() {
        if(ticket == null) {
            return null;
        }
        return ticket.getUserDetails();
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAllocated() {
        return allocated;
    }

    public void setAllocated(boolean allocated) {
        this.allocated = allocated;
    }
}
