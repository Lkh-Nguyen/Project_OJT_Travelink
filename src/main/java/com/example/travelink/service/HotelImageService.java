package com.example.travelink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelink.model.HotelImage;
import com.example.travelink.repository.HotelImageRepository;
import java.util.List;

@Service
public class HotelImageService {
    @Autowired
    private HotelImageRepository hotelImageRepository;

    public List<HotelImage> GetHotelInformationByHotelId(int hotelId) {
        return hotelImageRepository.findByHotel_HotelId(hotelId);
    }
}
