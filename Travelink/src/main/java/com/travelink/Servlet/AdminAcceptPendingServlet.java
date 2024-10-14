/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.travelink.Servlet;

import com.travelink.Database.AccountDB;
import com.travelink.Database.PendingHostDB;
import com.travelink.Model.Account;
import com.travelink.Model.PendingHost;
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
 * @author DUYAN
 */
public class AdminAcceptPendingServlet extends HttpServlet {
   
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
            out.println("<title>Servlet AdminAcceptPendingServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminAcceptPendingServlet at " + request.getContextPath () + "</h1>");
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
        List<PendingHost> pendings = new ArrayList<>();
        pendings = PendingHostDB.getAllPendingHost();
        if (pendings == null){
            request.setAttribute("error", "Don't have any pending host");
        }
        else {
            request.setAttribute("pendings", pendings);
        }
        request.getRequestDispatcher("Admin_Pending_Hotel.jsp").forward(request, response);
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
        String method = request.getParameter("method");
        int pendingId = Integer.parseInt(request.getParameter("pendingId"));
        PendingHost pendingHost = PendingHostDB.getPendingHostById(pendingId);
        SendEmail mail = new SendEmail();
        if (method.equalsIgnoreCase("accept")){
            if (pendingHost != null) {
            // Tạo một Account mới từ thông tin của PendingHost
            Account account = new Account();
            account.setEmail(pendingHost.getEmail());
            account.setPassword(pendingHost.getPassword());
            account.setCmnd(pendingHost.getCmnd());
            account.setName(pendingHost.getName());
            account.setGender(pendingHost.getGender());
            account.setDateOfBirth(pendingHost.getDateOfBirth());
            account.setPhoneNumber(pendingHost.getPhoneNumber());
            account.setAvatarURL(pendingHost.getAvatarURL());
            account.setAddress(pendingHost.getAddress());
            account.setRole(2); // Set role là 2

            // Lưu Account mới vào bảng Account
            AccountDB.insertAccount(account);

            // Xóa PendingHost khỏi bảng Pending_Host
            PendingHostDB.deletePendingHost(pendingId);

            mail.sendHotelEmail(pendingHost.getEmail(),"accepted");
        }
        }
        else{
            PendingHostDB.deletePendingHost(pendingId);
            mail.sendHotelEmail(pendingHost.getEmail(), "rejected");
        }

        response.sendRedirect("AdminAcceptPendingServlet");
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
