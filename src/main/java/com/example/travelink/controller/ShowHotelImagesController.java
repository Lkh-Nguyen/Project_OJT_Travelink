package com.example.travelink.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.travelink.model.Hotel;
import com.example.travelink.model.HotelFacility;
import com.example.travelink.model.HotelImage;
import com.example.travelink.service.HotelFacilityService;
import com.example.travelink.service.HotelImageService;
import com.example.travelink.service.HotelService;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShowHotelImagesController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelImageService hotelImageService;
    @Autowired
    private HotelFacilityService hotelFacilityService;

    @GetMapping("/ShowHotelImages")
    public String getMethodName(@RequestParam int hotelId, Model model) {
        Hotel hotel = hotelService.getHotelById(hotelId);
        List<HotelFacility> hotelFacilities = hotelFacilityService.getFacilitiesByHotelId(hotelId);
        List<HotelImage> hotelImages = hotelImageService.GetHotelInformationByHotelId(hotelId);
        model.addAttribute("hotel", hotel);
        model.addAttribute("hotelFacilities", hotelFacilities);
        model.addAttribute("hotelImages", hotelImages);
        return "ShowHotel_Images";
    }

}
