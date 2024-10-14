/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.ReservationDB;
import com.travelink.Database.ReservedRoomDB;
import com.travelink.Database.RoomDB;
import com.travelink.Model.Account;
import com.travelink.Model.Reservation;
import com.travelink.Model.ReservedRoom;
import com.travelink.Model.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertOfflineBookingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String number_of_guestsStr = request.getParameter("number_of_guests");
        int number_of_guests = Integer.parseInt(number_of_guestsStr);
        String checkInDateStr = request.getParameter("checkInDate");
        String checkOutDateStr = request.getParameter("checkOutDate");

        //Parsing date
        java.sql.Date checkInDate = java.sql.Date.valueOf(checkInDateStr);
        java.sql.Date checkOutDate = java.sql.Date.valueOf(checkOutDateStr);

        //Get data
        String totalPriceStr = request.getParameter("totalPrice");
        int totalPrice = Integer.parseInt(totalPriceStr);
        String paymentMethod = request.getParameter("paymentMethod");
        String status = request.getParameter("status");
        String bookingStr = request.getParameter("bookingStr");
        Map<Room, Integer> bookingMap = getBookingsFromBookingString(bookingStr);
        
        //Check available room again
        List<Reservation> check1 = ReservationDB.reservationCoincide(checkInDate, checkOutDate);
        for (Map.Entry<Room, Integer> entry : bookingMap.entrySet()) {
            Room room = entry.getKey();
            int amount = entry.getValue();
            int availableRoom = RoomDB.numberOfRoomAvailableByTime(room.getRoom_ID(), checkInDate, checkOutDate, check1);
            if (availableRoom >= amount) {
                continue;
            } else {
                response.sendRedirect("Error.jsp");
                return;
            }
        }

        //Insert reservation
        Reservation reservation = new Reservation();
        reservation.setReservationDate(LocalDateTime.now());
        reservation.setNumber_of_guests(number_of_guests);
        reservation.setCheckInDate(checkInDate);
        reservation.setCheckOutDate(checkOutDate);
        reservation.setTotalPrice(totalPrice);
        reservation.setPaymentMethod(paymentMethod);
        reservation.setStatus(status);
        reservation.setAccount_ID(account.getAccount_ID());
        int newReservationID = ReservationDB.insertReservationAndGetID(reservation);

        //Insert reserved room
        for (Map.Entry<Room, Integer> entry : bookingMap.entrySet()) {
            Room room = entry.getKey();
            int amount = entry.getValue();
            ReservedRoom reservedRoom = new ReservedRoom();
            reservedRoom.setAmount(amount);
            reservedRoom.setReservation_ID(newReservationID);
            reservedRoom.setRoom_ID(room.getRoom_ID());
            ReservedRoomDB.insertReservedRoom(reservedRoom);
        }

        //Message and redirect
        session.setAttribute("insertOfflineBookingMessage", "Insert offline booking success!");
        response.sendRedirect("HotelHost_BookingScheduleServlet");
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
