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
import com.travelink.Model.Province;
import com.travelink.Model.HotelPartner;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class AdminHotelPartnerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<OwnedHotel> ownedHotels = OwnedHotelDB.getAllOwnedHotels();
        //Create new view
        List<HotelPartner> partnerList = new ArrayList<>();
        for (OwnedHotel ownedHotel : ownedHotels) {
            Hotel hotel = HotelDB.getHotelByID(ownedHotel.getHotel_ID());
            Account account = AccountDB.getAccountByAccountID(ownedHotel.getAccount_ID());
            String province = ProvinceDB.getLocationByHotelID(ownedHotel.getHotel_ID());
            HotelPartner partner = new HotelPartner(hotel, account, province);
            partnerList.add(partner);
            request.setAttribute("partnerList", partnerList);
        }
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
