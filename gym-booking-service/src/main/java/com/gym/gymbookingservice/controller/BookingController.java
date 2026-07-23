package com.gym.gymbookingservice.controller;

import com.gym.gymbookingservice.dto.BookingRequestDto;
import com.gym.gymbookingservice.dto.BookingResponseDto;
import com.gym.gymbookingservice.service.BookingService;
// Habilita la validacion automatica (@Valid) del DTO de entrada segun sus anotaciones jakarta.validation
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// Permite inyectar el JWT ya validado directamente como parametro del metodo del controlador
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/bookings")
public class BookingController {


    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(
            @Valid @RequestBody BookingRequestDto request,
            @AuthenticationPrincipal Jwt jwt) {
        String memberUsername = jwt.getClaimAsString("preferred_username");
        BookingResponseDto created = bookingService.createBooking(request, memberUsername);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @GetMapping("/my-bookings")
    public ResponseEntity<List<BookingResponseDto>> getMyBookings(@AuthenticationPrincipal Jwt jwt) {
        String memberUsername = jwt.getClaimAsString("preferred_username");
        return ResponseEntity.ok(bookingService.getBookingsByMember(memberUsername));
    }

    @GetMapping("/my-classes")
    public ResponseEntity<List<BookingResponseDto>> getMyClasses(@AuthenticationPrincipal Jwt jwt) {
        String trainerUsername = jwt.getClaimAsString("preferred_username");
        return ResponseEntity.ok(bookingService.getBookingsByTrainer(trainerUsername));
    }
    @PatchMapping("/{id}/attend")
    public ResponseEntity<BookingResponseDto> markAsAttended(
            @PathVariable Long id,
            @AuthenticationPrincipal Jwt jwt) {
        String trainerUsername = jwt.getClaimAsString("preferred_username");
        return ResponseEntity.ok(bookingService.markAsAttended(id, trainerUsername));
    }


    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

}
