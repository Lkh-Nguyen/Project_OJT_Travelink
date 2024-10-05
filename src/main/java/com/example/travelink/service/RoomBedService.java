package com.example.travelink.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelink.model.RoomBed;
import com.example.travelink.repository.RoomBedRepository;

@Service
public class RoomBedService {

    @Autowired
    private RoomBedRepository roomBedRepository;

    // public List<RoomBed> getRoomBedsByHotelId(int hotelId) {
    // return roomBedRepository.findRoomBedsByHotelId(hotelId);

    // }

    public List<RoomBed> getRoomBedsByRoomId(int roomId) {
        return roomBedRepository.findByRoom_RoomId(roomId);
    }
}
