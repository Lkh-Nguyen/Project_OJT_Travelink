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
        List<Hotel> hotelsWithImages = new ArrayList<>(); // Danh sách lưu trữ khách sạn
        Map<Integer, List<HotelImage>> hotelImagesMap = new HashMap<>(); // Bản đồ lưu trữ hình ảnh của từng khách sạn

        // Lặp qua danh sách khách sạn yêu thích
        for (FavouriteHotel favouriteHotel : favouriteHotels) {
            Hotel hotel = favouriteHotel.getHotel(); // Lấy khách sạn từ FavouriteHotel
            List<HotelImage> images = hotelPictureService.getImagesByHotelId(hotel.getHotelId()); // Lấy hình ảnh của
                                                                                                  // khách sạn
            hotelImagesMap.put(hotel.getHotelId(), images); // Lưu hình ảnh vào bản đồ theo ID khách sạn

            System.out.println("Hotel ID: " + hotel.getHotelId() + ", Name: " + hotel.getName() + ", Images Count: "
                    + images.size());
            hotelsWithImages.add(hotel); // Thêm khách sạn vào danh sách
        }

        System.out.println("Number of favourite hotels: " + favouriteHotels.size());

        // Thêm danh sách khách sạn và hình ảnh vào mô hình
        model.addAttribute("hotels", hotelsWithImages);
        model.addAttribute("hotelImagesMap", hotelImagesMap); // Thêm bản đồ hình ảnh vào mô hình
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
            redirectAttributes.addFlashAttribute("error", "Vui lòng đăng nhập trước!"); // Thông báo lỗi
            return "redirect:/CustomerLoginRegister"; // Chuyển hướng đến trang đăng nhập
        }

        int accountID = customer.getAccountId();
        favouriteHotelService.deleteFavouriteHotel(hotelID, accountID);

        if (favouriteHotelRepository.existsByHotel_HotelIdAndAccount_AccountId(hotelID, accountID)) {
            // Fail delete
            return "redirect:/ViewAllHotel";
        } else {
            return "redirect:/CustomerFavouriteHotel"; // Chuyển hướng đến danh sách yêu thích
        }
    }

}
