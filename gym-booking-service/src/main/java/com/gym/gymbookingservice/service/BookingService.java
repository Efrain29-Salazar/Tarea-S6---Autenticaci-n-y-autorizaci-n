package com.gym.gymbookingservice.service;


import com.gym.gymbookingservice.dto.BookingRequestDto;
// DTO de salida usado en todas las respuestas
import com.gym.gymbookingservice.dto.BookingResponseDto;

import java.util.List;


public interface BookingService {


    BookingResponseDto createBooking(BookingRequestDto request, String memberUsername);

    List<BookingResponseDto> getBookingsByMember(String memberUsername);

    List<BookingResponseDto> getBookingsByTrainer(String trainerUsername);

    BookingResponseDto markAsAttended(Long bookingId, String trainerUsername);

    List<BookingResponseDto> getAllBookings();

    void deleteBooking(Long bookingId);

}
