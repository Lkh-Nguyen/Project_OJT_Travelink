/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.RefundingReservationDB;
import com.travelink.Database.ReservationDB;
import com.travelink.Model.RefundingReservation;
import com.travelink.Model.Reservation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author ASUS
 */
public class CancelBookingServlet extends HttpServlet {

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
        String reservation_IDStr = request.getParameter("reservation_ID");
        //Null bill_ID or invalid ID
        int reservation_ID;
        try {
            reservation_ID = Integer.parseInt(reservation_IDStr);
        } catch (Exception e) {
            response.sendRedirect("Error.jsp");
            return;
        }
        //Take out the reservation check in date
        Reservation reservation = ReservationDB.getReservationByReservationID(reservation_ID);
        LocalDate checkinDate = reservation.getCheckInDate().toLocalDate();

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the difference in days
        long daysUntilCheckin = ChronoUnit.DAYS.between(currentDate, checkinDate);

        //Cancel Policy of Travelink Hotel
        if (daysUntilCheckin >= 3) {
            //Setting refunding reservation attribute
            RefundingReservation refundingReservation = new RefundingReservation();
            refundingReservation.setReservationId(reservation_ID);
            refundingReservation.setCancelDate(LocalDateTime.now());
            refundingReservation.setStatus("NOT PAID");
            refundingReservation.setRefundTime(null);
            //Setting the reservation status
            ReservationDB.changedReservationStatusByReservationID(reservation_ID, "REFUNDING");
            //If cancel day >= 5, refund 100% money
            if (daysUntilCheckin >= 5){
                refundingReservation.setAmount(reservation.getTotalPrice());
            } 
            //If cancel day >= 3 && < 5, refund 70% money
            else {
                refundingReservation.setAmount(reservation_ID * 70 / 100);
            }
            RefundingReservationDB.insertRefundingReservation(refundingReservation);
        }
        //If cancel day < 3 , no refund
        else{
            ReservationDB.changedReservationStatusByReservationID(reservation_ID, "CANCEL");
        }

        response.sendRedirect("Cancel_Hotel_Service");
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
