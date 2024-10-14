
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
import java.util.Random;

/**
 *
 * @author DUYAN
 */
public class VerifyCodeServlet extends HttpServlet {

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
            out.println("<title>Servlet VerifyCodeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyCodeServlet at " + request.getContextPath() + "</h1>");
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
        Random rand = new Random();
        int token = rand.nextInt(900000) + 100000;
        String cToken = ((Integer) token).toString();
        //Store info of Account and token on session
        request.getSession().setAttribute("cToken", cToken);
        //Send email token
        SendEmail mail = new SendEmail();
        String email = ((Account) request.getSession().getAttribute("newAccount")).getEmail();
        mail.sendRegisterToken(email, token);
        request.setAttribute("errorMessage", "New token has been sent");
        request.getRequestDispatcher("Verify_Code.jsp").forward(request, response);
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
        String inputToken = request.getParameter("inputToken");
        String cToken = (String) request.getSession().getAttribute("cToken");
        String action = (String) request.getSession().getAttribute("action");
        String email = (String) request.getSession().getAttribute("email");
        int role = (int) request.getSession().getAttribute("roleU");
        if (inputToken.equals(cToken)) {
            if ("registerAccount".equals(action)) {
                Account c = (Account) request.getSession().getAttribute("newAccount");
//              Insert Account to DB
                boolean success = false;
                if (role == 1) {
                    success = AccountDB.insertAccount(c);
                    request.getRequestDispatcher("Form_Login.jsp").forward(request, response);
                    return;
                } else if (role == 2) {
                    PendingHost pending = new PendingHost(c.getEmail(), c.getPassword(),c.getCmnd(), c.getName(), c.getGender(),c.getDateOfBirth(), c.getPhoneNumber(), c.getAvatarURL(), c.getAddress());
                    success = PendingHostDB.insertPendingHost(pending);
                    request.getRequestDispatcher("HotelHost_Login.jsp").forward(request, response);
                    return;
                }
                if (success) {
                    request.setAttribute("successMessage", (role == 1) ? "Register successfully! Please login account again.":"Your hotel host account are in pending!!");
                }
            } else if ("forgetPassword".equals(action)) {
                String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                StringBuilder text = new StringBuilder(8);
                Random random = new Random();
                for (int i = 0; i < 8; i++) {
                    text.append(characters.charAt(random.nextInt(characters.length())));
                }
                SendEmail mail = new SendEmail();
                String textString = text.toString();
                mail.sendForgotPassword(email, textString);
                Account Account = AccountDB.getAccount(email);
                AccountDB.changePassword(Account, textString);
                request.setAttribute("updateMessage", "Your account has been created, your password has been sent to the email you just registered, please change your new password.");
                request.getRequestDispatcher("Form_Login.jsp").forward(request, response);
            }

        } //If inputToken wrong
        else {
            request.setAttribute("errorMessage", "Wrong verify code");
            request.getRequestDispatcher("Verify_Code.jsp").forward(request, response);
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
