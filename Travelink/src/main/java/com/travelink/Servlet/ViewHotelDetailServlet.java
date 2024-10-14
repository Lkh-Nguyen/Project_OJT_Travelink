/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.FavouriteHotelDB;
import com.travelink.Database.FeedbackDB;
import com.travelink.Database.HotelDB;
import com.travelink.Database.HotelFacilityDB;
import com.travelink.Database.HotelImageDB;
import com.travelink.Database.ReservationDB;
import com.travelink.Database.RoomDB;
import com.travelink.Database.RoomImageDB;
import com.travelink.Model.Account;
import com.travelink.Model.Facility;
import com.travelink.Model.Feedback;
import com.travelink.Model.Hotel;
import com.travelink.Model.HotelImage;
import com.travelink.Model.Reservation;
import com.travelink.Model.Room;
import com.travelink.Model.RoomImage;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author MSI
 */
public class ViewHotelDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("alterDeleteUnSuccess") != null) {
                request.setAttribute("alterDeleteUnSuccess", session.getAttribute("alterDeleteUnSuccess"));
                session.removeAttribute("alterDeleteUnSuccess");
            }
            if (session.getAttribute("alterDeleteSuccess") != null) {
                request.setAttribute("alterDeleteSuccess", session.getAttribute("alterDeleteSuccess"));
                session.removeAttribute("alterDeleteSuccess");
            }

            int hotel_ID = Integer.parseInt(request.getParameter("hotel_ID"));
            Hotel hotel = HotelDB.getHotelByID(hotel_ID);
            request.setAttribute("hotel_view", hotel);
            List<HotelImage> hotelImgList = HotelImageDB.getHotelImagesByHotelID(hotel_ID);

            // image center
            String urlHotelImgCenter = hotelImgList.get(0).getUrl();
            request.setAttribute("hotelImgCenter", urlHotelImgCenter);
            // image 4 image
            String urlHotelImg1 = hotelImgList.get(1).getUrl();
            String urlHotelImg2 = hotelImgList.get(2).getUrl();
            String urlHotelImg3 = hotelImgList.get(3).getUrl();
            String urlHotelImg4 = hotelImgList.get(4).getUrl();
            request.setAttribute("hotelImg1", urlHotelImg1);
            request.setAttribute("hotelImg2", urlHotelImg2);
            request.setAttribute("hotelImg3", urlHotelImg3);
            request.setAttribute("hotelImg4", urlHotelImg4);
            // list facility
            List<Facility> hotelFacilityList = HotelFacilityDB.getFacilitiesByHotelID(hotel_ID);
            request.setAttribute("hotelFacilityList", hotelFacilityList);
            // list room hotel
            List<Room> listRoom = RoomDB.getActiveRoomsByHotel_ID(hotel_ID);
            request.setAttribute("roomList", listRoom);
            // list img room hotel
            List<RoomImage> roomImgList = new ArrayList<>();
            for (Room room : listRoom) {
                roomImgList.add(RoomImageDB.getRoomImagesByRoom_ID(room.getRoom_ID()).get(0));
            }
            request.setAttribute("roomImgList", roomImgList);
            // checkFavorite

            Account account = (Account) session.getAttribute("account");
            if (account != null) {
                boolean checkFavorite = FavouriteHotelDB.getFavoriteHotel(hotel_ID, account.getAccount_ID());
                request.setAttribute("checkFavorite", checkFavorite);
            }

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
            } catch (Exception e) {
                e.printStackTrace();

                // Set beginDate to today
                beginDate = new java.util.Date();

                // Set endDate to the next day
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(beginDate);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                endDate = calendar.getTime();

                // Update the date strings to reflect the new default values
                checkInDateString = dateFormat.format(beginDate);
                checkOutDateString = dateFormat.format(endDate);
            }
            
            
            //Parse number of guests
            String number_of_guestsString = request.getParameter("number_of_guests");
            int number_of_guests;
            try{
                number_of_guests = Integer.parseInt(number_of_guestsString);
            }catch(Exception ex){
                ex.printStackTrace();
                number_of_guests = 1;
            }

            List<Integer> numberOfRoomList = new ArrayList<>();
            List<Reservation> check1 = ReservationDB.reservationCoincide(beginDate, endDate);
            for (Room room : RoomDB.getActiveRoomsByHotel_ID(hotel_ID)) {
                if (check1 == null) {
                    int numberOfRoom = room.getTotalRooms();
                    numberOfRoomList.add(numberOfRoom);
                } else {
                    int numberOfRoom = RoomDB.numberOfRoomAvailableByTime(room.getRoom_ID(), beginDate, endDate, check1);
                    numberOfRoomList.add(numberOfRoom);
                }
            }
            request.setAttribute("numberOfRoomList", numberOfRoomList);
            List<Feedback> feedbacks = FeedbackDB.getFeedbacksByHotelID(hotel_ID);
            request.setAttribute("number_of_guests", number_of_guests);
            request.setAttribute("check_in_date", checkInDateString);
            request.setAttribute("check_out_date", checkOutDateString);
            request.setAttribute("feedbacks", feedbacks);
            // checkFavorite
            request.getRequestDispatcher("Hotel_Detail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");
            String statusFav = request.getParameter("statusFav");
            String idHotelFavor = request.getParameter("idHotelFavor");
            String checkedFavorite = request.getParameter("checkedFavorite");
            if (statusFav.equalsIgnoreCase("yes")) {
                if (checkedFavorite != null) {
                    session.setAttribute("alterDeleteUnSuccess", "Favourite hotel is already existing !");
                } else {
                    if (FavouriteHotelDB.deleteFavouriteHotel(Integer.parseInt(idHotelFavor), account.getAccount_ID())) {
                        session.setAttribute("alterDeleteSuccess", "Delete favorite hotel succesfully.");
                    }
                }
            } else if (statusFav.equalsIgnoreCase("no")) {
                if (checkedFavorite == null) {
                    session.setAttribute("alterDeleteUnSuccess", "Delete favorite hotel unsuccesfully.");
                } else {
                    if (FavouriteHotelDB.addFavouriteHotel(Integer.parseInt(idHotelFavor), account.getAccount_ID())) {
                        session.setAttribute("alterDeleteSuccess", "Add favorite hotel succesfully.");
                    }
                }
            }
            response.sendRedirect("viewHotelDetailServlet?hotel_ID=" + idHotelFavor);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("Error.jsp");
        }
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
