package com.example.travelink.repository;

import com.example.travelink.model.HotelFacility;
import com.example.travelink.model.HotelFacility.HotelFacilityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelFacilityRepository extends JpaRepository<HotelFacility, HotelFacilityKey> {
    List<HotelFacility> findByHotel_HotelId(int hotelId);
}
