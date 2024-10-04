package com.example.travelink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.travelink.model.HotelFacility;
import com.example.travelink.repository.HotelFacilityRepository;
import java.util.List;
@Service
public class HotelFacilityService {
    @Autowired
    private HotelFacilityRepository hotelFacilityRepository;
    public List<HotelFacility> getFacilitiesByHotelId(int hotelId){
        return hotelFacilityRepository.findByHotel_HotelId(hotelId);
    }
}
