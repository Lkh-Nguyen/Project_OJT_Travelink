/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.OwnedHotelDB;
import com.travelink.Database.RoomDB;
import com.travelink.Model.Account;
import com.travelink.Model.Hotel;
import com.travelink.Model.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author admin
 */
public class UpdateHotelRoomServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateHotelRoomServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateHotelRoomServlet at " + request.getContextPath() + "</h1>");
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

        if (account == null) {
            response.sendRedirect("HotelHost_Login.jsp");
            return;
        }


        List<Hotel> list_hotels = OwnedHotelDB.getHotelsByAccountID(account.getAccount_ID());

        int hotelID = 0;
        if (request.getParameter("hotel_ID") != null) {
            hotelID = Integer.parseInt(request.getParameter("hotel_ID"));
        } else if (list_hotels != null && !list_hotels.isEmpty()) {
            hotelID = list_hotels.get(0).getHotel_ID();
        } else {
            response.sendRedirect("ErrorPage.jsp");
            return;
        }

        List<Room> list_rooms = RoomDB.getRoomsByHotel_ID(hotelID);

        request.setAttribute("hotel_list", list_hotels);
        request.setAttribute("room_list", list_rooms);
        request.setAttribute("hotel_id", hotelID);
        request.getRequestDispatcher("HotelHost_RoomInformation.jsp").forward(request, response);
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
        String action = request.getParameter("action");

        switch (action) {
            case "update":
                updateRoom(request, response);
                break;
            case "add":
                addRoom(request, response);
                break;
            default:
                break;
        }
    }

    //Function update room
    private void updateRoom(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int hotel_ID = Integer.parseInt(request.getParameter("hotelid"));
        int roomID = Integer.parseInt(request.getParameter("roomid"));
        // Retrieve room details from the form
        Room room = new Room();
        room.setName(request.getParameter("name"));
        room.setRoomDescription(request.getParameter("description"));
        room.setCapacity(Integer.parseInt(request.getParameter("capacity")));
        room.setTotalRooms(Integer.parseInt(request.getParameter("totalRooms")));
        room.setPrice(Integer.parseInt(request.getParameter("price")));
        room.setStatus(request.getParameter("status"));

        boolean updated = RoomDB.updateRoom(hotel_ID, roomID, room);

        if (updated) {
            // Update successful
            response.sendRedirect("UpdateHotelRoomServlet?hotel_ID=" + hotel_ID + "&status=success&message=Room%20Update%20Successfully");
        } else {
            // Update failed
            response.sendRedirect("UpdateHotelRoomServlet?hotel_ID=" + hotel_ID + "&status=failure&message=Update%20Room%20Failed");
        }
    }
    
    
    //Function add room
    private void addRoom(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int hotel_ID = Integer.parseInt(request.getParameter("hotel_ID"));
        System.out.println("Hotel ID: " + hotel_ID);  // Add this line

        Room room = new Room();
        room.setName(request.getParameter("name"));
        room.setRoomDescription(request.getParameter("description"));
        room.setCapacity(Integer.parseInt(request.getParameter("capacity")));
        room.setTotalRooms(Integer.parseInt(request.getParameter("totalRooms")));
        room.setPrice(Integer.parseInt(request.getParameter("price")));
        room.setStatus(request.getParameter("status"));

        boolean added = RoomDB.addRoom(hotel_ID, room);

        if (added) {
            response.sendRedirect("UpdateHotelRoomServlet?hotel_ID=" + hotel_ID + "&status=success&message=Room%20Add%20Successfully");
        } else {
            response.sendRedirect("UpdateHotelRoomServlet?hotel_ID=" + hotel_ID + "&status=failure&message=Add%20Room%20Failed");
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
