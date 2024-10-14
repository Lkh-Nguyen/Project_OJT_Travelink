/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.AccountDB;
import com.travelink.Database.ProvinceDB;
import com.travelink.Model.Account;
import com.travelink.Model.Province;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import javax.mail.Session;

/**
 *
 * @author MSI
 */
public class LoginAccountServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginAccountServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginAccountServlet at " + request.getContextPath() + "</h1>");
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
            response.sendRedirect("Error.jsp");
        }
        switch (account.getRole()) {
            case 1:
                response.sendRedirect("homeCustomerServlet");
                break;
            case 2:
                response.sendRedirect("homeHotelHostServlet");
                break;
            case 3:
                response.sendRedirect("AdminDashBoardServlet");
                break;
            default:
                break;
        }
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
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Account cu = AccountDB.getAccount(email);
        String roleStr = request.getParameter("role");
        String forwardPage = "Home_Customer.jsp";
        int role = Integer.parseInt(roleStr);
//        PrintWriter printWriter = response.getWriter();
//        printWriter.println(email);
//        printWriter.print(password);
//        printWriter.print(cu);
        if (cu == null) {
            request.setAttribute("errorLogin", "Email does not exist.");
            forwardPage = (role == 2) ? "HotelHost_Login.jsp" : "Form_Login.jsp";
        } else if (!password.equals(cu.getPassword())) {
            request.setAttribute("errorLogin", "Password is incorrect.");
            forwardPage = (role == 2) ? "HotelHost_Login.jsp" : "Form_Login.jsp";
        } else {
            if (cu.getRole() == 3) {
                session.setAttribute("successLogin", "Login successful.");
                session.setAttribute("account", cu);
                response.sendRedirect("AdminDashBoardServlet");
                return;
            } else if (cu.getRole() != role) {
                request.setAttribute("errorLogin", "You are logging in with the wrong permission role!");
                forwardPage = (role == 1) ? "HotelHost_Login.jsp" : "Form_Login.jsp";
            } else if (cu.getStatus() == 2) {
                request.setAttribute("errorLogin", "Your account has been banned!");
                forwardPage = (role == 2) ? "HotelHost_Login.jsp" : "Form_Login.jsp";
            } else {
                session.setAttribute("account", cu);
                if (role == 1) {
//                    List<Province> locationList = ProvinceDB.getAllProvince();
//                    request.setAttribute("locationList", locationList);
//                    request.setAttribute("successLogin", "Login successful.");
//                    request.getRequestDispatcher("Home_Customer.jsp").forward(request, response);
                    session.setAttribute("successLogin", "Login successful.");
                    response.sendRedirect("homeCustomerServlet");
                } else if (role == 2) {
                    response.sendRedirect("homeHotelHostServlet");
                }
                return;
            }
        }
        request.getRequestDispatcher(forwardPage).forward(request, response);
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
