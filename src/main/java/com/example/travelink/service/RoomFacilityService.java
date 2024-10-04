package com.example.travelink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.travelink.model.RoomFacility;
import com.example.travelink.repository.RoomFacilityRepository;

import java.util.List;
@Service
public class RoomFacilityService {
    @Autowired
    private RoomFacilityRepository roomFacilityRepository;
    public List<RoomFacility> getFacilitiesByRoomId(int roomId){
        return roomFacilityRepository.findByRoom_RoomId(roomId);
    }
}
