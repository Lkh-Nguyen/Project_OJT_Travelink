/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.travelink.Servlet;

import com.travelink.Database.HotelDB;
import com.travelink.Database.HotelImageDB;
import com.travelink.Database.OwnedHotelDB;
import com.travelink.Database.RoomDB;
import com.travelink.Database.RoomImageDB;
import com.travelink.Model.Account;
import com.travelink.Model.Hotel;
import com.travelink.Model.Room;
import com.travelink.Model.RoomImage;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class HotelHost_DeleteHotelServlet extends HttpServlet {
   
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
            out.println("<title>Servlet HotelHost_DeleteHotelServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HotelHost_DeleteHotelServlet at " + request.getContextPath () + "</h1>");
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
        int hotelID = Integer.parseInt(request.getParameter("hotelID"));
        Hotel hotel = HotelDB.getHotelByID(hotelID);
        Hotel newHotel = hotel;
        if(hotel.getStatus().equals("ACTIVE")){
            newHotel.setStatus("INACTIVE");
            HotelDB.updateHotel(newHotel, hotel);
            request.setAttribute("addStatus", "Change hotel status succesfull");
        }else if(hotel.getStatus().equals("INACTIVE")){
            List<Room> roomList = RoomDB.getRoomsByHotel_ID(hotelID);
            List<RoomImage> roomImages = new ArrayList<>();
            for(Room room : roomList){
                for(RoomImage image : RoomImageDB.getRoomImagesByRoom_ID(room.getRoom_ID())){
                    roomImages.add(image);
                }
            }
            if(HotelImageDB.getHotelImagesByHotelID(hotelID).size()>= 5 
               && roomList.size() > 0 
               && roomImages.size() >= (roomList.size()*5)){
                
                newHotel.setStatus("ACTIVE");
                HotelDB.updateHotel(newHotel, hotel);
            }else{
                request.setAttribute("addStatus", "Hotel :"+hotel.getName()+"   "+" 'You must add full Information!'");
            }
            System.out.println(roomList.size());
            System.out.println(roomImages.size());
            System.out.println(HotelImageDB.getHotelImagesByHotelID(hotelID).size());
        }
        
        HttpSession session = request.getSession();
        Account hotelHost = (Account) session.getAttribute("account");
        request.setAttribute("hotel_list", OwnedHotelDB.getHotelsByAccountID(hotelHost.getAccount_ID()));
        request.getRequestDispatcher("HotelHost_HotelInformation.jsp").forward(request, response);
        
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
