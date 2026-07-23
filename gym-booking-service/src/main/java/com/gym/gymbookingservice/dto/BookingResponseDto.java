package com.gym.gymbookingservice.dto;


import com.gym.gymbookingservice.model.BookingStatus;

import java.time.LocalDateTime;


public record BookingResponseDto(


        Long id,
        String memberUsername,
        String trainerUsername,
        String className,
        LocalDateTime classDate,
        BookingStatus status

) {
}
