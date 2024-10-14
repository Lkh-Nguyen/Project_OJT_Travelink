/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.HotelDB;
import com.travelink.Database.ReservationDB;
import com.travelink.Database.RoomDB;
import com.travelink.Model.Account;
import com.travelink.Model.Hotel;
import com.travelink.Model.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Home_Customer.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        //If user already have not paid online bill
        if (ReservationDB.hasUnpaidReservationWithVietQR(account.getAccount_ID())) {
            response.sendRedirect("NotPaid_Hotel_Service");
            return;
        }

        //Get parameter from hotel detail jsp
        String bookingStr = request.getParameter("bookingStr");
        String hotel_IDStr = request.getParameter("hotel_ID");
        int hotel_ID = Integer.parseInt(hotel_IDStr);

        //Set Attribute
        Hotel hotel = HotelDB.getHotelByID(hotel_ID);
        Map<Room, Integer> bookingMap = getBookingsFromBookingString(bookingStr);
        int totalPriceInt = calculateTotalPrice(bookingMap);
        String totalPriceStr = Integer.toString(totalPriceInt);

        // Retrieve the date strings from the request parameters
        String checkInDateString = request.getParameter("check_in_date");
        String checkOutDateString = request.getParameter("check_out_date");

        // Define the date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Declare Date variables outside the try-catch block
        java.util.Date beginDate = null;
        java.util.Date endDate = null;

        try {
            // Parse the date strings into Date objects
            beginDate = dateFormat.parse(checkInDateString);
            endDate = dateFormat.parse(checkOutDateString);
        } catch (ParseException e) {
            // Handle the exception if the date parsing fails
            e.printStackTrace();
            response.sendRedirect("Error.jsp");
            return;
        }

        String number_of_guestsStr = request.getParameter("number_of_guests");
        int number_of_guests;
        try {
            number_of_guests = Integer.parseInt(number_of_guestsStr);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("Error.jsp");
            return;
        }

        //If valid set session and redirect
        request.setAttribute("bookingStr", bookingStr);
        request.setAttribute("bookingMap", bookingMap);
        request.setAttribute("bookingHotel", hotel);
        request.setAttribute("bookingTotalPrice", totalPriceStr);
        request.setAttribute("checkInDate", checkInDateString);
        request.setAttribute("checkOutDate", checkOutDateString);
        request.setAttribute("number_of_guests", number_of_guests);
        request.getRequestDispatcher("Checkout.jsp").forward(request, response);
    }

    //Handle the booking string
    private static Map<Room, Integer> getBookingsFromBookingString(String bookingStr) {
        Map<Room, Integer> map = new HashMap<>();
        int totalPrice = 0;
        String[] bookings = bookingStr.split("/");
        for (String s : bookings) {
            String[] detail = s.split(",");
            String room_IDStr = detail[0];
            String quantityStr = detail[1];
            Room room = RoomDB.getRoomByRoomID(takeValue(room_IDStr));
            int quantity = takeValue(quantityStr);
            map.put(room, quantity);
            totalPrice = quantity * room.getPrice();
            System.out.println(totalPrice);
        }
        return map;
    }

    //Take out the value of each parameter in booking string
    private static int takeValue(String valueStr) {
        String[] splitStr = valueStr.split("=");
        String resultStr = splitStr[1];
        int result = Integer.parseInt(resultStr);
        return result;
    }

    private static int calculateTotalPrice(Map<Room, Integer> bookingMap) {
        int totalPrice = 0;

        // Iterate through the map entries
        for (Map.Entry<Room, Integer> entry : bookingMap.entrySet()) {
            Room room = entry.getKey();
            int quantity = entry.getValue();

            // Calculate total price for each room and add it to the running total
            totalPrice += room.getPrice() * quantity;
        }

        return totalPrice;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
