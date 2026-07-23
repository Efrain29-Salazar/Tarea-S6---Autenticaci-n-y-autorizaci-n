package com.gym.gymbookingservice;


import org.springframework.boot.SpringApplication;
// Importa la anotacion que marca esta clase como el punto de entrada de la aplicacion
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GymBookingServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(GymBookingServiceApplication.class, args);
    }

}
