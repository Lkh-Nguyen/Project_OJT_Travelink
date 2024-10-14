/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.HotelDB;
import com.travelink.Database.MonthlyPaymentDB;
import com.travelink.Database.OwnedHotelDB;
import com.travelink.Database.RefundingReservationDB;
import com.travelink.Database.ReservationDB;
import com.travelink.Model.Account;
import com.travelink.Model.Hotel;
import com.travelink.Model.MonthlyPayment;
import com.travelink.Model.RefundingReservation;
import com.travelink.Model.Reservation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        List<Hotel> hotelList = OwnedHotelDB.getHotelsByAccountID(account.getAccount_ID());
        //If the hotel host has no hotel yet
        if (hotelList.isEmpty() || hotelList == null) {
            System.out.println("No owned hotel yet!");
            response.sendRedirect("Error.jsp");
            return;
        }
        request.setAttribute("hotelList", hotelList);
        String hotel_IDStr = request.getParameter("hotel_ID");
        String monthStr = request.getParameter("month");
        String yearStr = request.getParameter("year");
        int hotel_ID = -1;
        int month = -1;
        int year = -1;
        //If one of the parameter missing
        if (hotel_IDStr == null || monthStr == null || yearStr == null) {
            //If hotel_ID missing, take the first hotel from the list
            if (hotel_IDStr == null) {
                hotel_ID = hotelList.get(0).getHotel_ID();
            }
            //If monthStr missing
            if (monthStr == null) {
                month = 0;
                request.setAttribute("month", month);
            }
            //If yearStr missing
            if (yearStr == null) {
                year = LocalDate.now().getYear();
                request.setAttribute("year", year);
            }
        }
        //Check if any available
        try {
            if (hotel_IDStr != null) {
                hotel_ID = Integer.parseInt(hotel_IDStr);
            }
            if (monthStr != null) {
                month = Integer.parseInt(monthStr);
            }
            if (yearStr != null) {
                year = Integer.parseInt(yearStr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("Error.jsp");
            return;
        }

        //Check if the hotel does not exist or the month is invalid
        Hotel hotel = HotelDB.getHotelByID(hotel_ID);
        if (month < 0 || month > 12 || hotel == null) {
            response.sendRedirect("Error.jsp");
            return;
        }

        //Check the owner of the hotel 
        System.out.println("Hotel id: " + hotel_ID);
        Account hotelOwner = OwnedHotelDB.getAccountByHotelID(hotel_ID);
        //If the login hotel host not the owner 
        if (!hotelOwner.getEmail().equalsIgnoreCase(account.getEmail())) {
            System.out.println("Not equals");
            response.sendRedirect("Error.jsp");
            return;
        }

        //Get all online reservation
        List<Reservation> reservationList = ReservationDB.getVietQRReservationsByHotelID(hotel_ID);
        //Filtered the reservation
        List<Reservation> filteredReservations = ReservationDB.filterByCheckoutMonthAndYear(reservationList, month, year);
        List<Reservation> paidReservations = ReservationDB.filterByStatus(filteredReservations, "PAID");
        List<Reservation> finishedReservations = ReservationDB.filterByStatus(filteredReservations, "FINISHED");
        List<Reservation> feedbackedReservations = ReservationDB.filterByStatus(filteredReservations, "FEEDBACKED");
        List<Reservation> cancelReservations = ReservationDB.filterByStatus(filteredReservations, "CANCEL");
        //Reservation waiting for refund
        List<Reservation> refundingReservations = ReservationDB.filterByStatus(filteredReservations, "REFUNDING");
        //Reservation that already + waiting for refund
        List<RefundingReservation> refundingReservations2 = RefundingReservationDB.getRefundingReservationsByReservations(filteredReservations);
        List<Reservation> reservationInRefundingReservation = ReservationDB.getReservationsInRefundingReservations(refundingReservations2);
        //Return the reservation statistics
        request.setAttribute("hotel_ID", hotel_ID);
        request.setAttribute("totalReservations", filteredReservations.size());
        request.setAttribute("paidReservations", paidReservations.size() + finishedReservations.size() + feedbackedReservations.size());
        request.setAttribute("cancelReservations", cancelReservations.size() + refundingReservations.size());
        request.setAttribute("refundReservations", refundingReservations2.size());

        // Get year monthly payment for bar chart
        List<MonthlyPayment> monthlyPayments = MonthlyPaymentDB.getAllMonthlyPaymentsByHotelID(hotel_ID, year);

        // Create an array with 12 elements initialized to 0
        int[] monthlyAmounts = new int[12];

        // Populate the array with the amounts from the monthlyPayments list
        for (MonthlyPayment payment : monthlyPayments) {
            int monthIndex = payment.getMonth() - 1; // Convert 1-based month to 0-based index
            monthlyAmounts[monthIndex] = payment.getAmount();
        }

        // Convert the array to a string
        StringBuilder amountsStringBuilder = new StringBuilder("[");
        for (int i = 0; i < monthlyAmounts.length; i++) {
            amountsStringBuilder.append(monthlyAmounts[i]);
            if (i < monthlyAmounts.length - 1) {
                amountsStringBuilder.append(", ");
            }
        }
        amountsStringBuilder.append("]");

        String barDataString = amountsStringBuilder.toString();
        request.setAttribute("barDataString", barDataString);

        //Calculate balance
        //Cant take monthly revenue because it can changes 
        //These statistics is only temporary, only constant when month is over
        //Calculate balance, paid income and refund income
        int paidReservationIncome = 0;
        paidReservationIncome += sumOfReservations(paidReservations);
        paidReservationIncome += sumOfReservations(finishedReservations);
        paidReservationIncome += sumOfReservations(feedbackedReservations);
        paidReservationIncome = paidReservationIncome * 90 / 100;
        int refundedReservationIncome = 0;
        refundedReservationIncome += sumOfReservations(reservationInRefundingReservation);
        refundedReservationIncome -= sumOfRefundingReservations(refundingReservations2);
        refundedReservationIncome = refundedReservationIncome * 90 / 100;
        int balance = paidReservationIncome + refundedReservationIncome;
        request.setAttribute("balance", balance);
        request.setAttribute("paidReservationIncome", paidReservationIncome);
        request.setAttribute("refundedReservationIncome", refundedReservationIncome);

        //Forward
        request.getRequestDispatcher("HotelHost_DashBoard.jsp").forward(request, response);
    }

    //Calculate sum of reservations
    private static int sumOfReservations(List<Reservation> reservationList) {
        int sum = 0;
        for (Reservation reservation : reservationList) {
            sum += reservation.getTotalPrice();
        }
        return sum;
    }

    //Calculate sum of refunding reservations
    private static int sumOfRefundingReservations(List<RefundingReservation> refundingReservations) {
        int sum = 0;
        for (RefundingReservation refundingReservation : refundingReservations) {
            sum += refundingReservation.getAmount();
        }
        return sum;
    }
}
