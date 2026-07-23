package com.gym.gymbookingservice.exception;


public class BookingNotFoundException extends RuntimeException {


    public BookingNotFoundException(Long bookingId) {
        super("No se encontro la reserva con id: " + bookingId);
    }

}
