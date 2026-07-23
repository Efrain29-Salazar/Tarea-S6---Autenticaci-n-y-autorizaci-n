package com.gym.gymbookingservice.service.impl;

import com.gym.gymbookingservice.dto.BookingRequestDto;
import com.gym.gymbookingservice.dto.BookingResponseDto;
import com.gym.gymbookingservice.exception.BookingAccessDeniedException;
import com.gym.gymbookingservice.exception.BookingNotFoundException;
import com.gym.gymbookingservice.exception.InvalidBookingStateException;
import com.gym.gymbookingservice.model.BookingStatus;
import com.gym.gymbookingservice.model.ClassBooking;
import com.gym.gymbookingservice.repository.ClassBookingRepository;
import com.gym.gymbookingservice.service.BookingService;
// Marca la clase como un componente de servicio administrado por Spring
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {


    private final ClassBookingRepository bookingRepository;


    public BookingServiceImpl(ClassBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public BookingResponseDto createBooking(BookingRequestDto request, String memberUsername) {

        ClassBooking booking = new ClassBooking();
        booking.setMemberUsername(memberUsername);
        booking.setTrainerUsername(request.trainerUsername());
        booking.setClassName(request.className());
        booking.setClassDate(request.classDate());
        booking.setStatus(BookingStatus.RESERVED);
        ClassBooking saved = bookingRepository.save(booking);
        return toResponseDto(saved);
    }

    @Override
    public List<BookingResponseDto> getBookingsByMember(String memberUsername) {
        return bookingRepository.findByMemberUsername(memberUsername)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public List<BookingResponseDto> getBookingsByTrainer(String trainerUsername) {
        return bookingRepository.findByTrainerUsername(trainerUsername)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public BookingResponseDto markAsAttended(Long bookingId, String trainerUsername) {
        ClassBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException(bookingId));

        if (!booking.getTrainerUsername().equals(trainerUsername)) {
            throw new BookingAccessDeniedException();
        }

        if (booking.getStatus() != BookingStatus.RESERVED) {
            throw new InvalidBookingStateException(
                    "Solo se puede marcar asistencia sobre una reserva en estado RESERVED. Estado actual: " + booking.getStatus());
        }

        booking.setStatus(BookingStatus.ATTENDED);
        ClassBooking updated = bookingRepository.save(booking);
        return toResponseDto(updated);
    }

    @Override
    public List<BookingResponseDto> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public void deleteBooking(Long bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new BookingNotFoundException(bookingId);
        }
        bookingRepository.deleteById(bookingId);
    }

    private BookingResponseDto toResponseDto(ClassBooking booking) {
        return new BookingResponseDto(
                booking.getId(),
                booking.getMemberUsername(),
                booking.getTrainerUsername(),
                booking.getClassName(),
                booking.getClassDate(),
                booking.getStatus()
        );
    }

}
