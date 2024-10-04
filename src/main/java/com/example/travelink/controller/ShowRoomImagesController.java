package com.example.travelink.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.travelink.model.Room;
import com.example.travelink.model.RoomFacility;
import com.example.travelink.model.RoomImage;
import com.example.travelink.service.RoomFacilityService;
import com.example.travelink.service.RoomImageService;
import com.example.travelink.service.RoomService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShowRoomImagesController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomImageService roomImageService;
    @Autowired
    private RoomFacilityService roomFacilityService;

    @GetMapping("/ShowRoomImages")
    public String getRoomDetails(@RequestParam int roomId, Model model) {
        Room room = roomService.getRoomById(roomId);
        List<RoomFacility> roomFacilities = roomFacilityService.getFacilitiesByRoomId(roomId);
        List<RoomImage> roomImages = roomImageService.getRoomImagesByRoomId(roomId);
        model.addAttribute("room", room);
        model.addAttribute("roomFacilities", roomFacilities);
        model.addAttribute("roomImages", roomImages);
        return "ShowRoom_Images";
    }

}
