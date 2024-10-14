/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.HotelDB;
import com.travelink.Database.MonthlyPaymentDB;
import com.travelink.Model.Account;
import com.travelink.Model.Hotel;
import com.travelink.Model.MonthlyPayment;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class AdminMonthlyPaymentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminMonthlyPaymentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminMonthlyPaymentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        
        String hotelname = request.getParameter("hotel_Name");
        
        if (account == null) {
            response.sendRedirect("HotelHost_Login.jsp");
            return;
        }

        List<Hotel> list_hotels = HotelDB.getAllHotels();
        int hotelID = 0;

        // Read hotel_ID parameter
        String hotelIDParam = request.getParameter("hotel_ID");
        if (hotelIDParam != null) {
            try {
                hotelID = Integer.parseInt(hotelIDParam);
            } catch (NumberFormatException e) {
                response.sendRedirect("Error.jsp");
                return;
            }
        } else if (list_hotels != null && !list_hotels.isEmpty()) {
            hotelID = list_hotels.get(0).getHotel_ID();
        } else {
            response.sendRedirect("Error.jsp");
            return;
        }

        int month = -1; // Default value
        int year = -1; // Default value

        // Read month and year parameters
        String monthParam = request.getParameter("month");
        if (monthParam != null) {
            try {
                month = Integer.parseInt(monthParam);
            } catch (NumberFormatException e) {
                response.sendRedirect("Error.jsp");
                return;
            }
        }

        String yearParam = request.getParameter("year");
        if (yearParam != null) {
            try {
                year = Integer.parseInt(yearParam);
            } catch (NumberFormatException e) {
                response.sendRedirect("Error.jsp");
                return;
            }
        }

        List<MonthlyPayment> adminMonthly = new ArrayList<>();

        // Fetch monthly payments based on parameters
        if (month == 0 || year < 0) {
            // No month or year specified
            adminMonthly = MonthlyPaymentDB.getAllMonthlyPaymentsByHotelID(hotelID);
        } else if (month < 0) {
            // Year specified, no month specified
            if (year >= 2000 && year <= 2024) {
                adminMonthly = MonthlyPaymentDB.getAllMonthlyPaymentsByHotelID(hotelID, year);
            } else {
                response.sendRedirect("Error.jsp");
                return;
            }
        } else if (year < 0) {
            // Month specified, no year specified
            adminMonthly = MonthlyPaymentDB.getAllMonthlyPaymentsByHotelID(hotelID);
        } else {
            // Both month and year specified
            MonthlyPayment monthpay = MonthlyPaymentDB.getMonthlyPaymentByHotelIDYearMonth(hotelID, year, month);
            if (monthpay != null) {
                adminMonthly.add(monthpay);
            }
        }
        // Debugging output
        System.out.println("Hotel ID: " + hotelID);
        System.out.println("Month: " + month);
        System.out.println("Year: " + year);
        System.out.println("Number of payments found: " + adminMonthly.size());

        // Set attributes and forward request
        request.setAttribute("pay_lists", adminMonthly);
        request.setAttribute("hotels", list_hotels);
        request.setAttribute("hotel_id", hotelID);

        request.getRequestDispatcher("Admin_MonthlyPayment.jsp").forward(request, response);
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
        // Get the HTTP session
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        // Check if the user is logged in
        if (account == null) {
            response.sendRedirect("HotelHost_Login.jsp");
            return;
        }

        // Extract paymentID from request
        String paymentIdParam = request.getParameter("paymentID");
        if (paymentIdParam == null || paymentIdParam.isEmpty()) {
            response.sendRedirect("Error.jsp");
            return;
        }

        // Get Parameter
        int month;
        int year;
        int hotelid;

        try {
            month = Integer.parseInt(request.getParameter("paymentMonth"));
            year = Integer.parseInt(request.getParameter("paymentYear"));
            hotelid = Integer.parseInt(request.getParameter("hotelID"));
        } catch (NumberFormatException e) {
            response.sendRedirect("Error.jsp");
            return;
        }

        // Create Object
        MonthlyPayment monthlypay = MonthlyPaymentDB.getMonthlyPaymentByHotelIDYearMonth(hotelid, year, month);

        int paymentID;
        try {
            paymentID = Integer.parseInt(paymentIdParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("Error.jsp");
            return;
        }

        // Update the payment status
        boolean isUpdated = MonthlyPaymentDB.updatePaymentStatus(paymentID);

        // Check if monthlypay is null before using it
        if (monthlypay != null) {
            SendEmail sendemail = new SendEmail();
            sendemail.sendSuccessPayMonthlyPayment(monthlypay);
        } else {
            // Redirect to an error page if monthlypay is null
            response.sendRedirect("Error.jsp");
            return;
        }

        // Check if the update was successful
        if (isUpdated) {
            // Redirect to a success page or reload the list of payments
            response.sendRedirect("AdminMonthlyPaymentServlet?hotel_ID=" + request.getParameter("hotelID"));
        } else {
            // Redirect to an error page
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
