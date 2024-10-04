package com.example.travelink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.travelink.model.Hotel;
import com.example.travelink.model.HotelFacility;
import com.example.travelink.model.HotelImage;
import com.example.travelink.model.Room;
import com.example.travelink.model.RoomBed;
import com.example.travelink.model.RoomImage;
import com.example.travelink.service.HotelFacilityService;
import com.example.travelink.service.HotelImageService;
import com.example.travelink.service.HotelService;
import com.example.travelink.service.RoomBedService;
import com.example.travelink.service.RoomImageService;
import com.example.travelink.service.RoomService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerHotelDetailController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelImageService hotelImageService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomBedService roomBedService;
    @Autowired
    private RoomImageService roomImageService;
    @Autowired
    private HotelFacilityService hotelFacilityService;

    @GetMapping("/CustomerHotelDetail")
    public String GetAllHotelInformation(Model model, @RequestParam int hotelId) {
        Hotel hotel = hotelService.getHotelById(hotelId);
        List<HotelImage> hotelImages = hotelImageService.GetHotelInformationByHotelId(hotelId);
        List<Room> Room = roomService.getRoomsByHotelId(hotelId);
        List<HotelFacility> hotelFacilities = hotelFacilityService.getFacilitiesByHotelId(hotelId);
        if (hotel == null) {
            model.addAttribute("error", "Khách sạn không tồn tại.");
            return "errorPage"; // Chuyển đến trang lỗi
        }
        Map<Room, List<RoomBed>> roomBedMap = new HashMap<>();
        for (Room room : Room) {
            List<RoomBed> roomBeds = roomBedService.getRoomBedsByRoomId(room.getRoomId());
            roomBedMap.put(room, roomBeds);
        }
        Map<Integer, List<RoomImage>> roomImagesMap = new HashMap<>();
        for (Room room : Room) {
            List<RoomImage> images = roomImageService.getRoomImagesByRoomId(room.getRoomId());
            roomImagesMap.put(room.getRoomId(), images);
        }

        model.addAttribute("hotel", hotel);
        model.addAttribute("hotelImages", hotelImages);
        model.addAttribute("Room", Room);
        model.addAttribute("roomBedMap", roomBedMap);
        model.addAttribute("roomImagesMap", roomImagesMap);
        model.addAttribute("hotelFacilities", hotelFacilities);
        return "Customer_Hotel_Detail";
    }
}
