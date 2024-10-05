package com.example.travelink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.travelink.model.Account;
import com.example.travelink.model.FavouriteHotel;
import com.example.travelink.model.Hotel;
import com.example.travelink.model.HotelImage;
import com.example.travelink.repository.FavouriteHotelRepository;
import com.example.travelink.repository.HotelRepository;
import com.example.travelink.service.FavouriteHotelService;
import com.example.travelink.service.HotelPictureService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
@AllArgsConstructor
public class FavouriteHotelController {

    @Autowired
    private final FavouriteHotelService favouriteHotelService;

    @Autowired
    private final HotelRepository hotelRepository;

    @Autowired
    private final FavouriteHotelRepository favouriteHotelRepository;

    @Autowired
    private final HotelPictureService hotelPictureService;

    @GetMapping("/CustomerFavouriteHotel")
    public String viewFavouriteHotel(Model model, HttpSession session) {
        // Lấy thông tin tài khoản từ session
        Account customer = (Account) session.getAttribute("customer");

        // Kiểm tra xem khách hàng đã đăng nhập hay chưa
        if (customer == null) {
            return "redirect:/CustomerLoginRegister";
        }

        int accountId = customer.getAccountId();

        // Lấy danh sách khách sạn yêu thích của khách hàng
        List<FavouriteHotel> favouriteHotels = favouriteHotelService.getFavouriteHotelsByAccount(accountId);
        List<Hotel> hotelsWithImages = new ArrayList<>(); 
        Map<Integer, List<HotelImage>> hotelImagesMap = new HashMap<>(); 

        // Lặp qua danh sách khách sạn yêu thích
        for (FavouriteHotel favouriteHotel : favouriteHotels) {

            Hotel hotel = favouriteHotel.getHotel(); 
            List<HotelImage> images = hotelPictureService.getImagesByHotelId(hotel.getHotelId()); 

            hotelImagesMap.put(hotel.getHotelId(), images); 

            hotelsWithImages.add(hotel); 
        }

        model.addAttribute("hotels", hotelsWithImages);
        model.addAttribute("hotelImagesMap", hotelImagesMap); 
        model.addAttribute("customer", customer);

        return "Customer_Favourite_Hotel";
    }

    @GetMapping("/ViewAllHotel")
    public String viewAllHotels(Model model, HttpSession session) {
        // Thêm mã để lấy danh sách khách sạn và thêm vào model
        Account customer = (Account) session.getAttribute("customer");

        List<Hotel> hotels = hotelRepository.findAll();

        model.addAttribute("customer", customer);
        model.addAttribute("hotels", hotels);

        return "View_All_Hotel";
    }

    //Method add favourite hotel
    @PostMapping("/AddFavouriteHotel")
    public String addFavouriteHotel(@RequestParam int hotelID, RedirectAttributes redirectAttributes,
            HttpSession session) {

        Account customer = (Account) session.getAttribute("customer");

        if (customer == null) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng đăng nhập trước!");
            return "redirect:/ViewAllHotel";
        }

        if (favouriteHotelRepository.existsByHotel_HotelIdAndAccount_AccountId(hotelID, hotelID)) {
            return "redirect:/ViewAllHotel";
        }

        int accountID = customer.getAccountId();
        favouriteHotelService.addFavouriteHotel(hotelID, accountID);

        return "redirect:/CustomerFavouriteHotel";
    }

    @PostMapping("/DeleteFavouriteHotel")
    public String deleteFavouriteHotel(@RequestParam int hotelID, RedirectAttributes redirectAttributes,
            HttpSession session) {
        Account customer = (Account) session.getAttribute("customer");

        if (customer == null) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng đăng nhập trước!"); 
            return "redirect:/CustomerLoginRegister"; 
        }

        int accountID = customer.getAccountId();
        favouriteHotelService.deleteFavouriteHotel(hotelID, accountID);

        if (favouriteHotelRepository.existsByHotel_HotelIdAndAccount_AccountId(hotelID, accountID)) {
            return "redirect:/ViewAllHotel";
        } else {
            return "redirect:/CustomerFavouriteHotel"; 
        }
    }

}
