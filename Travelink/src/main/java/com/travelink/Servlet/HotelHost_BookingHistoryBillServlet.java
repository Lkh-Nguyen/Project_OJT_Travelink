/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.travelink.Servlet;

import com.travelink.Database.BillDB;
import com.travelink.Database.OwnedHotelDB;
import com.travelink.Model.Account;
import com.travelink.Model.Hotel;
import com.travelink.Model.Bill;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author DUYAN
 */
public class HotelHost_BookingHistoryBillServlet extends HttpServlet {
   
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
            out.println("<title>Servlet HotelHost_BookingHistoryBillServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HotelHost_BookingHistoryBillServlet at " + request.getContextPath () + "</h1>");
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
        int hotel_ID =  hotels.get(0).getHotel_ID();
        
        request.setAttribute("hotels", hotels);
        request.setAttribute("checked_Finish", true);
        request.setAttribute("checked_Paid", true);
        request.setAttribute("checked_Cancel", true);
        request.setAttribute("checked_Processing", true);
        request.setAttribute("checked_Feedback", true);
        request.setAttribute("checked_Refunding", true);
        List<Bill> listBillByHotel_ID = BillDB.getBillByHotelID(hotel_ID);
        request.setAttribute("listBillByHotel_ID", listBillByHotel_ID);
        request.setAttribute("hotel_ID", hotel_ID);
        request.getRequestDispatcher("HotelHost_BookingBill.jsp").forward(request, response);
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
        int hotel_ID = Integer.parseInt(request.getParameter("hotel_ID"));
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        List<Hotel> hotels = OwnedHotelDB.getHotelsByAccountID(account.getAccount_ID());
        request.setAttribute("hotels", hotels);
        int value = Integer.parseInt(request.getParameter("type"));
        if (value == 1) {
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            java.sql.Date dateOfStart = null;
            java.sql.Date dateOfEnd = null;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateOfStart = new java.sql.Date(dateFormat.parse(startDate).getTime());
            } catch (ParseException e) {
                e.printStackTrace(); // Handle parsing exception appropriately
            }
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateOfEnd = new java.sql.Date(dateFormat.parse(endDate).getTime());
            } catch (ParseException e) {
                e.printStackTrace(); // Handle parsing exception appropriately
            }
            List<Bill> listBillByHotel_ID = BillDB.getBillDateByHotelID(hotel_ID, dateOfStart, dateOfEnd);

            request.setAttribute("listBillByHotel_ID", listBillByHotel_ID);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("hotel_ID", hotel_ID);
            request.setAttribute("checked_Finish", true);
            request.setAttribute("checked_Paid", true);
            request.setAttribute("checked_Cancel", true);
            request.setAttribute("checked_Processing", true);
            request.setAttribute("checked_Feedback", true);
            request.setAttribute("checked_Refunding", true);
            request.getRequestDispatcher("HotelHost_BookingBill.jsp").forward(request, response);
        } else if (value == 2) {

            String checkedFinish = request.getParameter("checked_Finish");
            String checkedPaid = request.getParameter("checked_Paid");
            String checkedCancel = request.getParameter("checked_Cancel");
            String checkedProcessing = request.getParameter("checked_Processing");
            String checkedFeedback = request.getParameter("checked_Feedback");
            String checkedRefunding = request.getParameter("checked_Refunding");
            boolean checked_Finish = (checkedFinish != null);
            boolean checked_Paid = (checkedPaid != null);
            boolean checked_Cancel = (checkedCancel != null);
            boolean checked_Processing = (checkedProcessing != null);
            boolean checked_Feedback = (checkedFeedback != null);
            boolean checked_Refunding = (checkedRefunding != null);
            request.setAttribute("checked_Finish", checked_Finish);
            request.setAttribute("checked_Paid", checked_Paid);
            request.setAttribute("checked_Cancel", checked_Cancel);
            request.setAttribute("checked_Processing", checked_Processing);
            request.setAttribute("checked_Feedback", checked_Feedback);
            request.setAttribute("checked_Refunding", checked_Refunding);

            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            if (startDate == "" && endDate == "") {
                List<Bill> listBillByHotel_ID = BillDB.getBillByHotelID(hotel_ID);
                request.setAttribute("listBillByHotel_ID", listBillByHotel_ID);
                request.setAttribute("hotel_ID", hotel_ID);
                request.getRequestDispatcher("HotelHost_BookingBill.jsp").forward(request, response);
            } else {
                java.sql.Date dateOfStart = null;
                java.sql.Date dateOfEnd = null;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateOfStart = new java.sql.Date(dateFormat.parse(startDate).getTime());
                } catch (ParseException e) {
                    e.printStackTrace(); // Handle parsing exception appropriately
                }
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateOfEnd = new java.sql.Date(dateFormat.parse(endDate).getTime());
                } catch (ParseException e) {
                    e.printStackTrace(); // Handle parsing exception appropriately
                }
                List<Bill> listBillByHotel_ID = BillDB.getBillDateByHotelID(hotel_ID, dateOfStart, dateOfEnd);

                request.setAttribute("listBillByHotel_ID", listBillByHotel_ID);
                request.setAttribute("startDate", startDate);
                request.setAttribute("endDate", endDate);
                request.setAttribute("hotel_ID", hotel_ID);
                request.getRequestDispatcher("HotelHost_BookingBill.jsp").forward(request, response);
            }
        }
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
