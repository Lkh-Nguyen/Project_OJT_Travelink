/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.HotelDB;
import com.travelink.Database.HotelImageDB;
import com.travelink.Database.OwnedHotelDB;
import com.travelink.Model.Account;
import com.travelink.Model.Hotel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class AdminHotelInformationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String hotel_IDStr = request.getParameter("hotel_ID");
        //Missing hotel_ID
        if (hotel_IDStr == null) {
            response.sendRedirect("Error.jsp");
            return;
        }
        //Invalid hotel_ID format
        int hotel_ID = -1;
        try {
            hotel_ID = Integer.parseInt(hotel_IDStr);
            if (hotel_ID == -1) {
                response.sendRedirect("Error.jsp");
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("Error.jsp");
            return;
        }
        Hotel hotel = HotelDB.getHotelByID(hotel_ID);
        //Hotel not exists
        if (hotel == null) {
            response.sendRedirect("Error.jsp");
            return;
        }
        Account account = OwnedHotelDB.getAccountByHotelID(hotel_ID);
        String hotelImage = HotelImageDB.getHotelImagesByHotelID(hotel_ID).get(0).getUrl();
        request.setAttribute("hotel", hotel);
        request.setAttribute("account", account);
        request.setAttribute("hotelImage", hotelImage);
        request.getRequestDispatcher("Admin_Hotel_Information.jsp").forward(request, response);
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
