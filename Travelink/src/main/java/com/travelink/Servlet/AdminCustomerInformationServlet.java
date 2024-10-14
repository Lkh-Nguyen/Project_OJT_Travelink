/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.AccountDB;
import com.travelink.Database.FeedbackDB;
import com.travelink.Model.Account;
import com.travelink.Model.Feedback;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class AdminCustomerInformationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String account_IDStr = request.getParameter("account_ID");
        //Missing account_ID
        if (account_IDStr == null) {
            response.sendRedirect("Error.jsp");
            return;
        }
        //Invalid account_ID format
        int account_ID = -1;
        try {
            account_ID = Integer.parseInt(account_IDStr);
            if (account_ID == -1) {
                response.sendRedirect("Error.jsp");
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("Error.jsp");
            return;
        }
        Account account = AccountDB.getAccountByAccountID(account_ID);
        //Account not exists or not role customer
        if (account == null || account.getRole() != 1) {
            response.sendRedirect("Error.jsp");
            return;
        }
        
        List<Feedback> feedbackList = FeedbackDB.getFeedbacksByAccountID(account_ID);
                
        request.setAttribute("account", account);
        request.setAttribute("feedbackList", feedbackList);
        request.getRequestDispatcher("Admin_Customer_Information.jsp").forward(request, response);
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
