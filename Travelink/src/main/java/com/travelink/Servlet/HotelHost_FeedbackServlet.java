/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.travelink.Servlet;

import com.travelink.Database.FeedbackDB;
import com.travelink.Database.HotelDB;
import com.travelink.Database.OwnedHotelDB;
import com.travelink.Model.Account;
import com.travelink.Model.Feedback;
import com.travelink.Model.Hotel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author DUYAN
 */
public class HotelHost_FeedbackServlet extends HttpServlet {
   
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
            out.println("<title>Servlet HotelHost_FeedbackServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HotelHost_FeedbackServlet at " + request.getContextPath () + "</h1>");
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
        Account account = (Account) session.getAttribute("account");
        List<Hotel> hotels = OwnedHotelDB.getHotelsByAccountID(account.getAccount_ID());
        int hotelID =  hotels.get(0).getHotel_ID();
        
        request.setAttribute("hotels", hotels);
        List<Feedback> listFeedback = FeedbackDB.getFeedbacksByHotelID(hotelID);
        Hotel hotel = HotelDB.getHotelByID(hotelID);
        request.setAttribute("orderBy", "1");
        request.setAttribute("hotel", hotel);
        request.setAttribute("listFeedback", listFeedback);
        request.setAttribute("hotelID", hotelID);
        request.setAttribute("star", "0");
        request.getRequestDispatcher("HotelHost_Feedback.jsp").forward(request, response);
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
        
        int hotelID = Integer.parseInt(request.getParameter("hotelID"));
        String orderBy = request.getParameter("orderBy");
        List<Feedback> listFeedback = FeedbackDB.getFeedbacksByHotelID(hotelID);
        
        
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        List<Hotel> hotels = OwnedHotelDB.getHotelsByAccountID(account.getAccount_ID());
        request.setAttribute("hotels", hotels);
        
        if (orderBy.equalsIgnoreCase("2")) {
            Collections.sort(listFeedback, new Comparator<Feedback>() {
                @Override
                public int compare(Feedback f1, Feedback f2) {
                    return Integer.compare(f1.getRating(), f2.getRating());
                }
            });
        } else if (orderBy.equalsIgnoreCase("3")) {
            Collections.sort(listFeedback, new Comparator<Feedback>() {
                @Override
                public int compare(Feedback f1, Feedback f2) {
                    return Integer.compare(f2.getRating(), f1.getRating());
                }
            });
        }
        Hotel hotel = HotelDB.getHotelByID(hotelID);
        String star = request.getParameter("star");
        request.setAttribute("orderBy", orderBy);
        request.setAttribute("hotel", hotel);
        request.setAttribute("listFeedback", listFeedback);
        request.setAttribute("star", star);
        request.setAttribute("hotelID", hotelID);
        request.getRequestDispatcher("HotelHost_Feedback.jsp").forward(request, response);
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
