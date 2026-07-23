package com.gym.gymbookingservice.exception;


public class BookingAccessDeniedException extends RuntimeException {


    public BookingAccessDeniedException() {
        super("No tiene permisos sobre esta reserva: la clase no le pertenece");
    }

}
