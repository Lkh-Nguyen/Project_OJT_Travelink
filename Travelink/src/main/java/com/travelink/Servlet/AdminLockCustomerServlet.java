/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.AccountDB;
import com.travelink.Model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class AdminLockCustomerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String account_IDStr = request.getParameter("account_ID");
        int account_ID = Integer.parseInt(account_IDStr);
        Account account = AccountDB.getAccountByAccountID(account_ID);
        boolean isLocked = false;
        if (account.getStatus() == 1) {
            AccountDB.updateAccountStatus(account_ID, 2);
            isLocked = true;
        }
        else AccountDB.updateAccountStatus(account_ID, 1);
        SendEmail sendEmail = new SendEmail();
        sendEmail.sendAccountStatusEmail(account.getEmail(), isLocked);
        response.sendRedirect("AdminCustomerListServlet");
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
