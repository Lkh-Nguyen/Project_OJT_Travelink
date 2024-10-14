/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.travelink.Servlet;

import com.travelink.Database.AccountDB;
import com.travelink.Database.MonthlyPaymentDB;
import com.travelink.Database.PendingHostDB;
import com.travelink.Database.ProvinceDB;
import com.travelink.Database.ReservationDB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DUYAN
 */
public class AdminDashBoardServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet AdminDashBoardServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminDashBoardServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        String successLogin = (String) session.getAttribute("successLogin");
        session.removeAttribute("successLogin");
        int bookings = ReservationDB.getAllReservationsMonthly().size();
        int partners = AccountDB.getHotelPartner();
        int pendings = PendingHostDB.getAllPendingHost().size();
        int amount = MonthlyPaymentDB.getTotalMonthlyPaymentsByMonthAndYear(LocalDate.now().getMonthValue()-1, LocalDate.now().getYear());
        List<String> provinces = ProvinceDB.getTop3ProvincesWithMostReservations();
        if (provinces.size() < 3) provinces.add("");
        List<Integer> count = ProvinceDB.getTop3ProvincesReservations();
        if (count.size() <3) count.add(0);
        double revenue = Math.ceil((double) amount/9);
        List<Integer> monthlyPayments = new ArrayList<>();
        for (int month = 1; month <= LocalDate.now().getMonthValue(); month++) {
            int re = MonthlyPaymentDB.getTotalMonthlyPaymentsByMonthAndYear(month, LocalDate.now().getYear());
            monthlyPayments.add(re);
        }
        request.setAttribute("bookings",bookings);
        request.setAttribute("partners", partners);
        request.setAttribute("pendings",pendings);
        request.setAttribute("revenue", revenue);
        request.setAttribute("provinces", provinces);
        request.setAttribute("count", count);
        request.setAttribute("monthlyPayments", monthlyPayments);
        request.setAttribute("successLogin", successLogin);
        request.getRequestDispatcher("Admin_Dashboard.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
