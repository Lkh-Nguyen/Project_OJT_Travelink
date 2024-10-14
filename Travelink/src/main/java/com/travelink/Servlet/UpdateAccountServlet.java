/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.AccountDB;
import com.travelink.Model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author MSI
 */
public class UpdateAccountServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateAccountServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateAccountServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String dob = request.getParameter("DOB");
        String cmnd = request.getParameter("CMND");
        String phone = request.getParameter("phone");
        String genderJsp = request.getParameter("gender");
        System.out.println(genderJsp);
        String address = request.getParameter("address");
        java.sql.Date dateOfBirth = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateOfBirth = new java.sql.Date(dateFormat.parse(dob).getTime());
        } catch (ParseException e) {
            e.printStackTrace(); // Handle parsing exception appropriately
        }
        String phoneStr = null;
        if (phone == null) {
            phoneStr = null;
        } else if (phone != null) {
            phoneStr = phone;
        }
        char gender = ' ';
        if (genderJsp == null) {
            gender = ' ';
        } else if (genderJsp.contains("Male")) {
            gender = 'M';
        } else if (genderJsp.contains("Female")) {
            gender = 'F';
        }
        Account oldAccount = AccountDB.getAccount(email);
        Account newAccount = new Account(email, cmnd, name, gender, dateOfBirth, phoneStr, address, oldAccount.getRole(), oldAccount.getStatus());

        newAccount.setAccount_ID(oldAccount.getAccount_ID());
        newAccount.setPassword(oldAccount.getPassword());
        newAccount.setAvatarURL(oldAccount.getAvatarURL());
        newAccount.setAddress(oldAccount.getAddress());
        newAccount.setAddress(address);
        Account AccountUpdate = AccountDB.updateAccount(oldAccount, newAccount);
        HttpSession hs = request.getSession();
        hs.setAttribute("account", AccountUpdate);
        request.setAttribute("statusUpdate", "Update successfully Account.");
        PrintWriter printWriter = response.getWriter();
        //If user is customer
        if (oldAccount.getRole() == 1) {
            request.getRequestDispatcher("My_Account_Update.jsp").forward(request, response);
        } else if (oldAccount.getRole() == 2) {
            request.getRequestDispatcher("HotelHost_Profile.jsp").forward(request, response);
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
