/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.FeedbackDB;
import com.travelink.Database.ReportedFeedbackDB;
import com.travelink.Model.Feedback;
import com.travelink.Model.ReportedFeedback;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HELLO
 */
public class Admin_ViewReportFeedback extends HttpServlet {

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
            out.println("<title>Servlet Admin_ViewReportFeedback</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Admin_ViewReportFeedback at " + request.getContextPath() + "</h1>");
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
//     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Integer> listFeedback_ID = ReportedFeedbackDB.getAllReportedFeedback_ID();
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        for(int i=0;i<listFeedback_ID.size();i++){
            Feedback feedback = FeedbackDB.getFeedbackByFeedbackID(listFeedback_ID.get(i));
            feedbacks.add(feedback);
        }
        List<Integer> listCount = ReportedFeedbackDB.getCountReport();
        request.setAttribute("listCount", listCount);
        request.setAttribute("feedbacks", feedbacks);
        request.getRequestDispatcher("Admin_ReportFeedback.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        int feedbackID = Integer.parseInt(request.getParameter("feedbackID"));
        if(action.equalsIgnoreCase("cancel")){
            ReportedFeedbackDB.deleteReportedFeedbackByFeedbackId(feedbackID);
            request.setAttribute("cancelFeedback", "Cancel Reported Feedback Successfully");
        }else if(action.equalsIgnoreCase("delete")){
            ReportedFeedbackDB.deleteReportedFeedbackByFeedbackId(feedbackID);
            FeedbackDB.deleteFeedback(feedbackID);
            request.setAttribute("cancelFeedback", "Delete Reported Feedback Successfully");  
        }
        
        List<Integer> listFeedback_ID = ReportedFeedbackDB.getAllReportedFeedback_ID();
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        for(int i=0;i<listFeedback_ID.size();i++){
            Feedback feedback = FeedbackDB.getFeedbackByFeedbackID(listFeedback_ID.get(i));
            feedbacks.add(feedback);
        }
        List<Integer> listCount = ReportedFeedbackDB.getCountReport();
        request.setAttribute("listCount", listCount);
        request.setAttribute("feedbacks", feedbacks);
        request.getRequestDispatcher("Admin_ReportFeedback.jsp").forward(request, response);
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
