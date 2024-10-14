/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.HotelDB;
import com.travelink.Model.Account;
import com.travelink.Model.Hotel;
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
public class HotelHost_UpdateHotelServlet extends HttpServlet {

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
            out.println("<title>Servlet HotelHost_UpdateHotelServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HotelHost_UpdateHotelServlet at " + request.getContextPath() + "</h1>");
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
        int hotelID = Integer.parseInt(request.getParameter("hotelID"));
        Hotel hotel = HotelDB.getHotelByID(hotelID);
        request.setAttribute("hotel", hotel);
        request.getRequestDispatcher("HotelHost_UpdateHotelInformation.jsp").forward(request, response);
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
        int hotelID = Integer.parseInt(request.getParameter("hotelID"));
        Hotel hotel = HotelDB.getHotelByID(hotelID);
        if (!request.getParameter("name").equals(hotel.getName())) {
            HotelDB.updateNameHotel(hotelID, request.getParameter("name"));
            request.setAttribute("hotel", HotelDB.getHotelByID(hotelID));
            request.getRequestDispatcher("HotelHost_UpdateHotelInformation.jsp").forward(request, response);
        } else if (!request.getParameter("email").equals(hotel.getEmail())) {
            HotelDB.updateEmailHotel(hotelID, request.getParameter("email"));
            request.setAttribute("hotel", HotelDB.getHotelByID(hotelID));
            request.getRequestDispatcher("HotelHost_UpdateHotelInformation.jsp").forward(request, response);
        } else if (!request.getParameter("phone").equals(hotel.getPhoneNumber())) {
            HotelDB.updatePhoneNumberHotel(hotelID, request.getParameter("phone"));
            request.setAttribute("hotel", HotelDB.getHotelByID(hotelID));
            request.getRequestDispatcher("HotelHost_UpdateHotelInformation.jsp").forward(request, response);
        } else if (!request.getParameter("description").equals(hotel.getDescription())) {
            HotelDB.updateDescriptionHotel(hotelID, request.getParameter("description"));
            request.setAttribute("hotel", HotelDB.getHotelByID(hotelID));
            request.getRequestDispatcher("HotelHost_UpdateHotelInformation.jsp").forward(request, response);
        } else if (!request.getParameter("address").equals(hotel.getAddress())) {
            HotelDB.updateAddressHotel(hotelID, request.getParameter("address"));
            request.setAttribute("hotel", HotelDB.getHotelByID(hotelID));
            request.getRequestDispatcher("HotelHost_UpdateHotelInformation.jsp").forward(request, response);
        } else if (request.getParameter("name").equals(hotel.getName())
                && request.getParameter("email").equals(hotel.getEmail())
                && request.getParameter("phone").equals(hotel.getPhoneNumber())
                && request.getParameter("description").equals(hotel.getDescription())
                && request.getParameter("address").equals(hotel.getAddress())) {
            request.setAttribute("hotel", HotelDB.getHotelByID(hotelID));
            request.getRequestDispatcher("HotelHost_UpdateHotelInformation.jsp").forward(request, response);
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
