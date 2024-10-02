package com.example.travelink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.travelink.model.HotelImage;
import java.util.*;

@Repository
public interface HotelPictureRepository extends JpaRepository<HotelImage, Integer>{
    
    List<HotelImage> findByHotel_HotelId(int hotelId);

}
