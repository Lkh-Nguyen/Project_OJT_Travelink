package com.example.travelink.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelink.model.Room;
import com.example.travelink.repository.RoomRepository;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getRoomsByHotelId(int hotelId) {
        return roomRepository.findByHotel_hotelId(hotelId);
    }
}
