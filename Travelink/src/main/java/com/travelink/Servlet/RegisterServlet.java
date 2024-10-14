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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 *
 * @author DUYAN
 */
public class RegisterServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("Form_Login.jsp").forward(request, response);
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
        //Taking user input role
        String roleStr = request.getParameter("role");
        int role = 0;
        try {
            role = Integer.parseInt(roleStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Role = 1 => Customer
        if (role == 1) {
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            //Check Account existed
            Account c = AccountDB.getAccount(email);
            if (c != null) {
                request.setAttribute("errorLogin", "Email existed! You can not create account.");
                request.getRequestDispatcher("Form_Login.jsp").forward(request, response);
                return;
            }
            //Create Account
            c = new Account(email, password, name, phone, 1,1);
            c.setAvatarURL("/Travelink/img_Avatar/avatar_default.jpg");
            confirmation(request, response, c, email, 1);
        }
        //Role = 2 => Hotel Host
        else if (role == 2) {
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String dob = request.getParameter("DOB");
            String cmnd = request.getParameter("cmnd");
            String phone = request.getParameter("phone");
            String genderJsp = request.getParameter("gender");
            System.out.println(genderJsp);
            String address = request.getParameter("address");
            java.sql.Date dateOfBirth = null;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateOfBirth = new java.sql.Date(dateFormat.parse(dob).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
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
            //Check Account existed
            Account h = AccountDB.getAccount(email);
            if (h != null) {
                request.setAttribute("errorLogin", "Email existed! You can not create account.");
                request.getRequestDispatcher("HotelHost_Register.jsp").forward(request, response);
                return;
            }
            h = new Account(email, password, cmnd, name, gender, dateOfBirth, phoneStr, address, address, 2, 1);
            confirmation(request, response, h, email, 2);
        }
    }

    public static void confirmation(HttpServletRequest request, HttpServletResponse response, Account c, String email, int role) throws ServletException, IOException {
        //Random token
        Random rand = new Random();
        int token = rand.nextInt(900000) + 100000;
        String cToken = ((Integer) token).toString();
        //Store info of Account and token on session
        request.getSession().setAttribute("cToken", cToken);
        request.getSession().setAttribute("newAccount", c);
        //Send email token
        SendEmail mail = new SendEmail();
        mail.sendRegisterToken(email, token);
        //Request to verify code jsp
        HttpSession session = request.getSession();
        session.setAttribute("action", "registerAccount");
        session.setAttribute("roleU", role);
        response.sendRedirect("Verify_Code.jsp");
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
