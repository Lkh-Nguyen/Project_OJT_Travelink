/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.HotelDB;
import com.travelink.Database.OwnedHotelDB;
import com.travelink.Database.RoomDB;
import com.travelink.Model.Account;
import com.travelink.Model.Hotel;
import com.travelink.Model.Room;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author MSI
 */
public class HotelHost_BookingScheduleServlet extends HttpServlet {

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
            out.println("<title>Servlet HotelHost_BookingScheduleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HotelHost_BookingScheduleServlet at " + request.getContextPath() + "</h1>");
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
        String insertOfflineBookingMessage = (String) session.getAttribute("insertOfflineBookingMessage");
        if (insertOfflineBookingMessage != null){
            request.setAttribute("insertOfflineBookingMessage", insertOfflineBookingMessage);
            session.removeAttribute("insertOfflineBookingMessage");
        }
        Account hotelHostAccount = (Account) session.getAttribute("account");
        List<Hotel> hotelList = OwnedHotelDB.getHotelsByAccountID(hotelHostAccount.getAccount_ID());
        request.setAttribute("hotelList", hotelList);
        request.getRequestDispatcher("HotelHost_BookingSchedule.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account hotelHostAccount = (Account) session.getAttribute("account");
        List<Hotel> hotelList = OwnedHotelDB.getHotelsByAccountID(hotelHostAccount.getAccount_ID());
        request.setAttribute("hotelList", hotelList);
        if(request.getParameter("hotelID").equals("Selection hotel")){
            request.setAttribute("check", "Choice Hotel Please");
            request.getRequestDispatcher("HotelHost_BookingSchedule.jsp").forward(request, response);
        }
        int hotelID = Integer.parseInt(request.getParameter("hotelID"));
        List<Room> roomList = RoomDB.getRoomsByHotel_ID(hotelID);
        request.setAttribute("status", 1);
        // Set the room list as a request attribute
        request.setAttribute("roomList", roomList);
        request.setAttribute("hotel", HotelDB.getHotelByID(hotelID));
        // Forward the request to the JSP page
        request.getRequestDispatcher("HotelHost_BookingSchedule.jsp").forward(request, response);
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
