package com.gym.gymbookingservice.model;


import jakarta.persistence.*;
// Lombok: genera el constructor con todos los argumentos
import lombok.AllArgsConstructor;
// Lombok: genera getters, setters, equals, hashCode y toString
import lombok.Data;
// Lombok: genera el constructor vacio requerido por JPA
import lombok.NoArgsConstructor;

// Clase para representar fechas y horas de las clases
import java.time.LocalDateTime;


@Entity
@Table(name = "class_bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassBooking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "member_username", nullable = false)
    private String memberUsername;


    @Column(name = "trainer_username", nullable = false)
    private String trainerUsername;


    @Column(name = "class_name", nullable = false)
    private String className;


    @Column(name = "class_date", nullable = false)
    private LocalDateTime classDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookingStatus status = BookingStatus.RESERVED;

}
