package com.example.travelink.service;

import org.springframework.stereotype.Service;

import com.example.travelink.model.Hotel;
import com.example.travelink.repository.HotelRepository;

import lombok.AllArgsConstructor;

import java.util.*;

@Service
@AllArgsConstructor
public class HotelService {
    
    private final HotelRepository hotelRepository;

    //Get All Hotel
    public List<Hotel> findAll(){
        return hotelRepository.findAll();
    }


}
