/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.HotelDB;
import com.travelink.Database.HotelImageDB;
import com.travelink.Database.ProvinceDB;
import com.travelink.Database.ReservationDB;
import com.travelink.Database.RoomDB;
import com.travelink.Model.Hotel;
import com.travelink.Model.HotelImage;
import com.travelink.Model.Province;
import com.travelink.Model.Reservation;
import com.travelink.Model.Room;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DUYAN
 */
public class SearchHotelServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String location = request.getParameter("location");
            String url = ProvinceDB.getURLByProvinceName(location);
            int people = 0, roomSize = 0;
            try {
                people = Integer.parseInt(request.getParameter("number_of_people"));
                roomSize = Integer.parseInt(request.getParameter("number_of_rooms"));
            } catch (NumberFormatException e) {
                response.sendRedirect("Error.jsp");
                return;
            }
            String start_date = request.getParameter("check_in_date");
            String end_date = request.getParameter("check_out_date");
            java.sql.Date checkInDate = null;
            java.sql.Date checkOutDate = null;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                checkInDate = new java.sql.Date(dateFormat.parse(start_date).getTime());
                checkOutDate = new java.sql.Date(dateFormat.parse(end_date).getTime());
            } catch (ParseException e) {
                e.printStackTrace(); // Xử lý ngoại lệ phân tích cú pháp một cách thích hợp
                response.sendRedirect("Error.jsp");
                return;
            }
            
            LocalDate currentDate = LocalDate.now();
            LocalDate checkInDateLocal = checkInDate.toLocalDate();
            System.out.println("Checkin: " + checkInDate);
            System.out.println(currentDate);
            // check điều kiện ngày hiện tại và ngày check in
            if (currentDate.isAfter(checkInDateLocal)) {
                request.setAttribute("statusBeginDate", "Date checkin is disable");
                List<Province> locationList = ProvinceDB.getAllProvince();
                request.setAttribute("locationList", locationList);
                request.setAttribute("hotelList", null);
                request.getRequestDispatcher("Search_Hotel.jsp").forward(request, response);
            }
            // check điều kiệu số phòng và số người 
            if (roomSize > people) {
                request.setAttribute("statusRoomAndPeople", "Room and People is disable");
                List<Province> locationList = ProvinceDB.getAllProvince();
                request.setAttribute("locationList", locationList);
                request.setAttribute("hotelList", null);
                request.getRequestDispatcher("Search_Hotel.jsp").forward(request, response);
            }

            // kiểm tra điều kiện ngày bắt đầu và ngày kết thúc
            if (checkInDate.after(checkOutDate) || checkInDate.equals(checkOutDate)) {
                request.setAttribute("statusDate", "Date checkout is disable");
                List<Province> locationList = ProvinceDB.getAllProvince();
                request.setAttribute("locationList", locationList);
                request.setAttribute("hotelList", null);
                request.getRequestDispatcher("Search_Hotel.jsp").forward(request, response);
            } else {
                List<Hotel> newHotelList = new ArrayList<>();
                List<Hotel> hotelList = new ArrayList<>();
                try {
                    hotelList = HotelDB.filterProvince(location);
                } catch (SQLException ex) {
                    Logger.getLogger(SearchHotelServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                List<Reservation> check1 = ReservationDB.reservationCoincide(checkInDate, checkOutDate);
                List<Integer> hotelSizeList = new ArrayList<>();
                List<Integer> capacitySizeList = new ArrayList<>();
                for (Hotel hotel : hotelList) {
                    int roomHotelAvailable = 0;
                    int capacityHotelList = 0;
                    for (Room room : RoomDB.getActiveRoomsByHotel_ID(hotel.getHotel_ID())) {
                        roomHotelAvailable = roomHotelAvailable + RoomDB.numberOfRoomAvailableByTime(room.getRoom_ID(), checkInDate, checkOutDate, check1);
                        capacityHotelList = capacityHotelList + room.getCapacity() * roomHotelAvailable;
                    }
                    hotelSizeList.add(roomHotelAvailable);
                    capacitySizeList.add(capacityHotelList);
                }
                for (int i = 0; i < hotelList.size(); i++) {
                    if (hotelSizeList.get(i) >= roomSize && capacitySizeList.get(i) >= people) {
                        newHotelList.add(hotelList.get(i));
                    }
                }

                //If filter with star
                if (request.getParameter("star") != null){
                    int star = Integer.parseInt(request.getParameter("star"));
                    for (int i = 0; i < newHotelList.size(); i++){
                        if (newHotelList.get(i).getStar() != star){
                            newHotelList.remove(i);
                            i--;
                        }
                    }
                }
                
                List<String> hotelImageList = new ArrayList<>();
                for (int i = 0; i < newHotelList.size(); i++) {
                    List<HotelImage> hotelImgList = HotelImageDB.getHotelImagesByHotelID(newHotelList.get(i).getHotel_ID());
                    String img = hotelImgList.get(0).getUrl();
                    hotelImageList.add(img);
                }
                
                List<Province> locationList = ProvinceDB.getAllProvince();
                request.setAttribute("locationList", locationList);
                request.setAttribute("hotelList", newHotelList);
                request.setAttribute("hotelImgList", hotelImageList);
                request.setAttribute("url", url);
                PrintWriter printWriter = response.getWriter();
                printWriter.print(location);
                request.getRequestDispatcher("Search_Hotel.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("Error.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
