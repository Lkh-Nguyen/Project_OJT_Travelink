/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.DistrictDB;
import com.travelink.Database.FacilityDB;
import com.travelink.Database.HotelDB;
import com.travelink.Database.HotelFacilityDB;
import com.travelink.Database.OwnedHotelDB;
import com.travelink.Database.ProvinceDB;
import com.travelink.Database.WardDB;
import com.travelink.Model.Account;
import com.travelink.Model.Facility;
import com.travelink.Model.Hotel;
import com.travelink.Model.HotelFacility;
import com.travelink.Model.Ward;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author MSI
 */
public class HotelHost_AddHotelServlet extends HttpServlet {

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
            out.println("<title>Servlet HotelHost_AddHotelServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HotelHost_AddHotelServlet at " + request.getContextPath() + "</h1>");
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
        request.setAttribute("facilityList", FacilityDB.getAllFacilities());
        request.setAttribute("provinceList", ProvinceDB.getAllProvince());
        request.getRequestDispatcher("HotelHost_AddHotel.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int star = Integer.parseInt(request.getParameter("star"));
        String phoneNumber = request.getParameter("phoneNumber");
        String description = request.getParameter("description");
        String checkInTimeStart = request.getParameter("checkInTimeStart");
        String checkInTimeEnd = request.getParameter("checkInTimeEnd");
        String checkOutTimeStart = request.getParameter("checkOutTimeStart");
        String checkOutTimeEnd = request.getParameter("checkOutTimeEnd");
        // check time
        LocalTime checkInStart = LocalTime.parse(checkInTimeStart);
        LocalTime checkInEnd = LocalTime.parse(checkInTimeEnd);
        LocalTime checkOutStart = LocalTime.parse(checkOutTimeStart);
        LocalTime checkOutEnd = LocalTime.parse(checkOutTimeEnd);
        if (checkInStart.isAfter(checkInEnd) || checkOutStart.isAfter(checkOutEnd)) {
            session.setAttribute("status", "Time check in and check out not valid");
             response.sendRedirect("HotelHost_AddWardServlet");
             return;
        }
        for (Hotel h : HotelDB.getAllHotels()) {
            if (request.getParameter("email").equals(h.getEmail())) {
                session.setAttribute("status", "This email is contains");
                response.sendRedirect("HotelHost_AddWardServlet");
                return;
            }
            
        }
        if (request.getParameter("provinceID") != null) {
            request.setAttribute("provinceList", ProvinceDB.getAllProvince());
            request.setAttribute("districtList", DistrictDB.getDistrictsByProvinceID(Integer.parseInt(request.getParameter("provinceID"))));
            request.setAttribute("facilityList", FacilityDB.getAllFacilities());
        }

        String address = request.getParameter("address");
        int wardID = Integer.parseInt(request.getParameter("ward_ID"));

        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setEmail(email);
        hotel.setStar(star);
        hotel.setRating(0);
        hotel.setPhoneNumber(phoneNumber);
        hotel.setDescription(description);
        hotel.setCheckInTimeStart(LocalTime.parse(checkInTimeStart));
        hotel.setCheckInTimeEnd(LocalTime.parse(checkInTimeEnd));
        hotel.setCheckOutTimeStart(LocalTime.parse(checkOutTimeStart));
        hotel.setCheckOutTimeEnd(LocalTime.parse(checkOutTimeEnd));
        hotel.setAddress(address);
        hotel.setStatus("INACTIVE");
        hotel.setWard_ID(wardID);
        Hotel newHotel = HotelDB.addNewHotel(hotel);
        Account hotelHostAccount = (Account) session.getAttribute("account");
        request.setAttribute("hotel", hotel);
        Ward ward = WardDB.getWardByID(wardID);
        OwnedHotelDB.addNewOwned(hotelHostAccount.getAccount_ID(), newHotel.getHotel_ID());
        request.setAttribute("ward", ward);
        // facility
        for (Facility facility : FacilityDB.getAllFacilities()) {
            if (request.getParameter(facility.getName()) != null) {
                HotelFacilityDB.addHotelFacility(newHotel.getHotel_ID(), (Integer.parseInt(request.getParameter(facility.getName()))));
            }

        }
        request.setAttribute("facilityList", FacilityDB.getAllFacilities());
        request.setAttribute("updateStatus", "Add hotel successfull");
        request.getRequestDispatcher("HotelHost_AddHotel.jsp").forward(request, response);
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
