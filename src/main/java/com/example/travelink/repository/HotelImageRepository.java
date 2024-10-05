package com.example.travelink.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.travelink.model.HotelImage;
@Repository
public interface HotelImageRepository extends JpaRepository<HotelImage , Integer> {
    List<HotelImage> findByHotel_HotelId(int hotelId);
    
}