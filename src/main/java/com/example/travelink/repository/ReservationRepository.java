package com.example.travelink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.travelink.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
