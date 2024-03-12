package dev.santhoshkalisamy.cloudbeestrainreservation.exception;

import dev.santhoshkalisamy.cloudbeestrainreservation.model.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(TrainFullException.class)
    public ResponseEntity<Error> handleTrainFullException() {
        return ResponseEntity.status(400).body(new Error(101, "Sorry, train is full."));
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<Error> handleTicketNotFoundException() {
        return ResponseEntity.status(404).body(new Error(102, "Ticket details not found. Please check the id"));
    }

    @ExceptionHandler(SectionNotFoundException.class)
    public ResponseEntity<Error> handleSectionNotFoundException() {
        return ResponseEntity.status(404).body(new Error(103, "Section not found or no ticket has been allocated yet."));
    }

    @ExceptionHandler(SeatNotAvailableException.class)
    public ResponseEntity<Error> handleSeatNotAvailableException() {
        return ResponseEntity.status(400).body(new Error(104, "Sorry, requested seat not available"));
    }

}
