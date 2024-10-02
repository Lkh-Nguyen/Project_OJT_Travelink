package com.example.travelink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travelink.model.Hotel;

import java.util.List;


public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    
    List<Hotel> findAll();

    List<Hotel> findByName(String name);

}
