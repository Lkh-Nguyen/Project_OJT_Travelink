package com.example.travelink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelink.model.HotelImage;
import com.example.travelink.repository.HotelPictureRepository;

import lombok.AllArgsConstructor;
import java.util.*;

@Service
@AllArgsConstructor
public class HotelPictureService {
    
    @Autowired
    private final HotelPictureRepository hotelPictureRepository;

    //Get hotel images
    public List<HotelImage> getImagesByHotelId(int hotelId){
        return hotelPictureRepository.findByHotel_HotelId(hotelId);
    }

}
