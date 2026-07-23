package com.gym.gymbookingservice.repository;


import com.gym.gymbookingservice.model.ClassBooking;
// Interfaz base de Spring Data JPA: aporta CRUD, paginacion, etc. sin necesidad de implementarla
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClassBookingRepository extends JpaRepository<ClassBooking, Long> {



    List<ClassBooking> findByMemberUsername(String memberUsername);


    List<ClassBooking> findByTrainerUsername(String trainerUsername);

}
