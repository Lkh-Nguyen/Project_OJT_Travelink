/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.HotelDB;
import com.travelink.Database.ReservationDB;
import com.travelink.Database.RoomDB;
import com.travelink.Model.Hotel;
import com.travelink.Model.Reservation;
import com.travelink.Model.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class OfflineBookingServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //Đá về lại trang của a Nguyên
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Because using Post, no need for validating data
        //Getting data from the form
        String hotel_IDStr = request.getParameter("hotelID");
        int hotel_ID = Integer.parseInt(hotel_IDStr);
        Hotel hotel = HotelDB.getHotelByID(hotel_ID);
        String checkInDateStr = request.getParameter("startDate");
        String checkOutDateStr = request.getParameter("endDate");

        //Getting all the active room
        List<Room> roomList = RoomDB.getActiveRoomsByHotel_ID(hotel_ID);
        //Parsing Date 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date checkInDate = null;
        Date checkOutDate = null;

        try {
            checkInDate = dateFormat.parse(checkInDateStr);
            checkOutDate = dateFormat.parse(checkOutDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            response.sendRedirect("Error.jsp");
            return;
        }

        //Calculate the available room again and store in List
        List<Reservation> check1 = ReservationDB.reservationCoincide(checkInDate, checkOutDate);
        List<Integer> availableRoomList = new ArrayList<>();
        for (Room r : roomList) {
            int availableRoom = RoomDB.numberOfRoomAvailableByTime(r.getRoom_ID(), checkInDate, checkInDate, check1);
            availableRoomList.add(availableRoom);
        }

        //Passing attribute to jsp form 
        request.setAttribute("checkInDate", checkInDateStr);
        request.setAttribute("checkOutDate", checkOutDateStr);
        request.setAttribute("roomList", roomList);
        request.setAttribute("availableRoomList", availableRoomList);
        request.getRequestDispatcher("HotelHost_OfflineBooking.jsp").forward(request, response);
    }

}
