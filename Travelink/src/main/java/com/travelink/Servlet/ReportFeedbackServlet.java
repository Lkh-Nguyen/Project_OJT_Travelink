/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.FeedbackDB;
import com.travelink.Database.ReportedFeedbackDB;

import com.travelink.Model.Account;
import com.travelink.Model.Feedback;
import com.travelink.Model.ReportedFeedback;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 *
 * @author HELLO
 */
public class ReportFeedbackServlet extends HttpServlet {

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
            out.println("<title>Servlet ReportFeedbackServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReportFeedbackServlet at " + request.getContextPath() + "</h1>");
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
        String feedbackID = request.getParameter("feedbackID");
        String hotel_ID = request.getParameter("hotel_ID");
        HttpSession session = request.getSession();
        Account sessionAccount = (Account) session.getAttribute("account");
        if (sessionAccount == null) {
            request.getRequestDispatcher("Form_Login.jsp").forward(request, response);
        }
        if (feedbackID == null) {
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        } else {
            Feedback feedback = FeedbackDB.getFeedbackByFeedbackID(Integer.parseInt(feedbackID));
            if (sessionAccount.getAccount_ID() == feedback.getAccount(Integer.parseInt(feedbackID)).getAccount_ID()) {
                request.getRequestDispatcher("viewHotelDetailServlet?hotel_ID=" + hotel_ID).forward(request, response);
            }
            request.setAttribute("hotel_ID", hotel_ID);
            request.setAttribute("feedback", feedback);
            request.getRequestDispatcher("ReportFeedback.jsp").forward(request, response);
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
        String feedbackID = request.getParameter("feedbackID");
        String hotel_ID = request.getParameter("hotel_ID");
        HttpSession session = request.getSession();
        Account sessionAccount = (Account) session.getAttribute("account");
        if (ReportedFeedbackDB.checkReportFeedback(Integer.parseInt(feedbackID), sessionAccount.getAccount_ID())) {
            Feedback feedback123 = FeedbackDB.getFeedbackByFeedbackID(Integer.parseInt(feedbackID));
            request.setAttribute("feedback", feedback123);
            request.setAttribute("hotel_ID", hotel_ID);
            request.setAttribute("alterDeleteUnSuccess", "You have already reported this feedback");
            request.getRequestDispatcher("ReportFeedback.jsp").forward(request, response);
        } else {
            String reason = request.getParameter("reason");
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            ReportedFeedback feedback = new ReportedFeedback(currentTimestamp, reason, Integer.parseInt(feedbackID), sessionAccount.getAccount_ID());
            ReportedFeedbackDB.insertReportFeedback(feedback);
            Feedback feedback123 = FeedbackDB.getFeedbackByFeedbackID(Integer.parseInt(feedbackID));
            request.setAttribute("feedback", feedback123);
            request.setAttribute("hotel_ID", hotel_ID);
            request.setAttribute("alterDeleteSuccess", "Report feedback successfully");
            request.getRequestDispatcher("ReportFeedback.jsp").forward(request, response);
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
