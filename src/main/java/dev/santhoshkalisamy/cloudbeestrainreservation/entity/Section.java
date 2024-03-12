package dev.santhoshkalisamy.cloudbeestrainreservation.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    String name;
    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    List<Seat> seats;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
