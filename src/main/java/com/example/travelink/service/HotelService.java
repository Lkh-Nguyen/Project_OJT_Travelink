package com.example.travelink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.travelink.model.Hotel;
import com.example.travelink.repository.HotelRepository;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(int hotelId) {
        return hotelRepository.findById(hotelId).orElse(null);
    }

}
