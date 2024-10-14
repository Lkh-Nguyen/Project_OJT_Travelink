/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.AccountDB;
import com.travelink.Database.HotelDB;
import com.travelink.Database.OwnedHotelDB;
import com.travelink.Database.ProvinceDB;
import com.travelink.Model.Account;
import com.travelink.Model.Hotel;
import com.travelink.Model.OwnedHotel;
import com.travelink.Model.HotelPartner;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class AdminLockHotelServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminLockHotelServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminLockHotelServlet at " + request.getContextPath() + "</h1>");
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
        int hotelID = Integer.parseInt(request.getParameter("hotelID"));
        String type = request.getParameter("type");
        Hotel newHotel = HotelDB.getHotelByID(hotelID);
        boolean isLocked = false;
        if(type.equals("Lock")){
            Hotel newHotel_2 = newHotel;
            newHotel_2.setStatus("LOCKED");
            HotelDB.updateHotel(newHotel_2, newHotel);
            isLocked = true;
        }else{
            Hotel newHotel_2 = newHotel;
            newHotel_2.setStatus("ACTIVE");
            HotelDB.updateHotel(newHotel_2, newHotel);
        }
        SendEmail send = new SendEmail();
        send.sendHotelStatusEmail(newHotel.getEmail(), isLocked);
        List<OwnedHotel> ownedHotels = OwnedHotelDB.getAllOwnedHotels();
        //Create new view
        List<HotelPartner> partnerList = new ArrayList<>();
        for (OwnedHotel ownedHotel : ownedHotels) {
            Hotel hotel = HotelDB.getHotelByID(ownedHotel.getHotel_ID());
            Account account = AccountDB.getAccountByAccountID(ownedHotel.getAccount_ID());
            String province = ProvinceDB.getLocationByHotelID(ownedHotel.getHotel_ID());
            HotelPartner partner = new HotelPartner(hotel, account, province);
            partnerList.add(partner);
        }
        
        request.setAttribute("partnerList", partnerList);
        request.getRequestDispatcher("Admin_Hotel_Partner.jsp").forward(request, response);
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
        processRequest(request, response);
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
