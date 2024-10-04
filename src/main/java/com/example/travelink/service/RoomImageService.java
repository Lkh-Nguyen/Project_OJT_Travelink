package com.example.travelink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelink.model.RoomImage;
import com.example.travelink.repository.RoomImageRepository;
import java.util.List;

@Service
public class RoomImageService {
    @Autowired
    private RoomImageRepository roomImageRepository;

    public List<RoomImage> getRoomImagesByRoomId(int roomId) {
        return roomImageRepository.findByRoom_RoomId(roomId);
    }
}
