/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.AccountDB;
import com.travelink.Database.RefundingReservationDetailDB;
import com.travelink.Model.Account;
import com.travelink.Model.RefundingReservationViewDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class AdminRefundingReservationServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminRefundingReservationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminRefundingReservationServlet at " + request.getContextPath() + "</h1>");
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
        
        // Redirect to login if not logged in
        if (account == null) {
            response.sendRedirect("HotelHost_Login.jsp");
            return;
        }
        
        
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        LocalDateTime startDate = null;
        LocalDateTime endDate = null;

        // Parse date parameters
        try {
            if (startDateStr != null && !startDateStr.isEmpty()) {
                startDate = LocalDate.parse(startDateStr).atStartOfDay();
            }
            if (endDateStr != null && !endDateStr.isEmpty()) {
                endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);
            }
        } catch (DateTimeParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format");
            return;
        }

        // Get refunding reservations within the date range
        List<RefundingReservationViewDetail> refundingReservations = new ArrayList<>();
        if (startDate != null && endDate != null) {
            refundingReservations = RefundingReservationDetailDB.getRefundingDetail(startDate, endDate);
        } else {
            refundingReservations = RefundingReservationDetailDB.getAllRefundingReservationDetails();
        }

        request.setAttribute("reservation_lists", refundingReservations);
        request.getRequestDispatcher("Admin_RefundingReservationManagement.jsp").forward(request, response);
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
        String reservationIdParam = request.getParameter("reservationId");
        int reservation_Id = Integer.parseInt(reservationIdParam);
        String accountParam = request.getParameter("accountId");
        int account_Id = Integer.parseInt(accountParam);
        Account account = AccountDB.getAccountByAccountID(account_Id);

        // Set the refund time to the current date and time
        LocalDateTime refundTime = LocalDateTime.now();

        // Update the refund time in the database
        boolean success1 = RefundingReservationDetailDB.updateRefundTime(reservation_Id, refundTime);
        boolean success2 = RefundingReservationDetailDB.setStatusToPaid(reservation_Id);

        if (success1 && success2) {
            // Redirect to success page with message
            SendEmail sendmail = new SendEmail();
            sendmail.sendMailRefundingSuccess(account.getEmail(), reservation_Id);
            response.sendRedirect("AdminRefundingReservationServlet?status=success&message=Pay%20And%20SendEmail%20Successfully");
        } else {
            // Redirect to error page with message
            response.sendRedirect("AdminRefundingReservationServlet?status=failure&message=Pay%20And%20SendEmail%20Failed");
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
