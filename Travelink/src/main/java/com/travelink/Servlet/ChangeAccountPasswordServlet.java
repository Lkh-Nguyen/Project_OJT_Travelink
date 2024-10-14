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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author ASUS
 */
public class ChangeAccountPasswordServlet extends HttpServlet {

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
            out.println("<title>Servlet ChangeAccountPasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeAccountPasswordServlet at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect("My_Account_Change.jsp");
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
        String pass = request.getParameter("password");
        String newpass = request.getParameter("newpassword");
        String re_newpass = request.getParameter("re_newpassword");
        HttpSession session = request.getSession();
        Account Account = (Account) session.getAttribute("account");
        String forwardPath = "";
        if (Account.getRole() == 1) forwardPath = "My_Account_Change.jsp";
        if (Account.getRole() == 2) forwardPath = "HotelHost_ChangePassword.jsp";
        
        if (!Account.getPassword().equals(pass)) {
            request.setAttribute("error", "The old password is incorrect.");
            request.getRequestDispatcher(forwardPath).forward(request, response);
        } else if (pass.equalsIgnoreCase(newpass)) {
            request.setAttribute("error", "The new password must not be the same as the old password.");
            request.getRequestDispatcher(forwardPath).forward(request, response);
        } else if (!newpass.equalsIgnoreCase(re_newpass)) {
            request.setAttribute("error", "The new password does not match.");
            request.getRequestDispatcher(forwardPath).forward(request, response);
        } else if (AccountDB.changePassword(Account, newpass)) {
            request.setAttribute("updateStatus", "Updated password successfully.");
            Account.setPassword(newpass);
            session.setAttribute("account", Account);
            request.getRequestDispatcher(forwardPath).forward(request, response);
        } else {
            request.setAttribute("updateStatus", "Cập nhật mật khẩu không thành công!");
            request.getRequestDispatcher(forwardPath).forward(request, response);
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
