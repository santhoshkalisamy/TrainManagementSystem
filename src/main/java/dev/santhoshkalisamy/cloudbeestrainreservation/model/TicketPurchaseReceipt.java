package dev.santhoshkalisamy.cloudbeestrainreservation.model;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.Seat;
import dev.santhoshkalisamy.cloudbeestrainreservation.entity.UserDetails;

public record TicketPurchaseReceipt(int id, String from, String to, UserDetails userDetails, Double price, Integer seatNo, String section) {
}
