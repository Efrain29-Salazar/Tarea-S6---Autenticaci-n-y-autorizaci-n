package com.gym.gymbookingservice;

// Anotacion de JUnit 5 que marca este metodo como una prueba
import org.junit.jupiter.api.Test;
// Anotacion que levanta el contexto completo de Spring Boot durante la prueba
import org.springframework.boot.test.context.SpringBootTest;

// Prueba minima: verifica que el contexto de Spring arranca sin errores de configuracion
@SpringBootTest
class GymBookingServiceApplicationTests {

    @Test
    void contextLoads() {
        // Prueba intencionalmente vacia: si el contexto no carga, JUnit marca el test como fallido
    }

}
