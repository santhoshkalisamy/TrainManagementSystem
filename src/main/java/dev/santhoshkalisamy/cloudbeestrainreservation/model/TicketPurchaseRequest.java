package dev.santhoshkalisamy.cloudbeestrainreservation.model;

import dev.santhoshkalisamy.cloudbeestrainreservation.entity.UserDetails;

public record TicketPurchaseRequest(String from, String to, UserDetails userDetails) { }
