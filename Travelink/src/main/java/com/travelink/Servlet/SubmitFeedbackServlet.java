/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.source.tree.ContinueTree;
import com.travelink.Database.FeedbackDB;
import com.travelink.Database.ReservationDB;
import com.travelink.Model.Account;
import com.travelink.Model.Feedback;
import com.travelink.Model.Reservation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DUYAN
 */
public class SubmitFeedbackServlet extends HttpServlet {

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
            out.println("<title>Servlet SubmitFeedbackServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubmitFeedbackServlet at " + request.getContextPath() + "</h1>");
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
        try {

        } catch (Exception e) {
        }
        // Get parameters from the request
        String description = request.getParameter("description");
        try {
            if (checkFeedbackWithAI(description)){
                response.sendRedirect("Paid_Hotel_Service?check=false");
                return;
            }
        } catch (InterruptedException ex) {
            System.out.println("Error");
        }
        
        byte rating = Byte.parseByte(request.getParameter("rating"));
        // Assuming the date is already set in the hidden input field
        String dateStr = request.getParameter("date");
        // Assuming the reservation ID is passed from the previous page
        int reservationID = Integer.parseInt(request.getParameter("reservationID"));

        // Assuming you have the Account ID and Hotel ID from the session or elsewhere
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        int accountID = account.getAccount_ID();
        int hotelID = Integer.parseInt(request.getParameter("hotel_ID"));

        // Create a Feedback object
        Feedback feedback = new Feedback();
        feedback.setDescription(description);
        feedback.setRating(rating);
        feedback.setDate(java.sql.Date.valueOf(dateStr));
        feedback.setReservation_ID(reservationID);

        try {
            // Insert the feedback into the database
            FeedbackDB.insertFeedback(feedback);
            Reservation reservation = ReservationDB.getReservationByReservationID(reservationID);
            ReservationDB.changedReservationStatusByReservationID(reservationID, "FEEDBACKED");
            response.sendRedirect("Paid_Hotel_Service?check=true");
        } catch (Exception ex) {
            // Handle database errors
            ex.printStackTrace();
            response.sendRedirect("Error.jsp");
        }
    }

    public static boolean checkFeedbackWithAI(String description) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String uri = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent?key=AIzaSyDB0JmbQI_7sojgzZRSrnggqYp0qJ37p_0";
        // Creating the JSON body
        String jsonBody = "{\n"
                + "    \"contents\": [\n"
                + "        {\n"
                + "            \"parts\": [\n"
                + "                {\n"
                + "                    \"text\": \"Answer only 'True' or 'False', is this description containing any harmful or violence languages? Description: " + description + "\"\n"
                + "                }\n"
                + "            ]\n"
                + "        }\n"
                + "    ]\n"
                + "}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

        // Extract the text part and the finishReason part
        JsonArray candidates = jsonObject.getAsJsonArray("candidates");
        if (candidates != null && candidates.size() > 0) {
            JsonObject firstCandidate = candidates.get(0).getAsJsonObject();
            String finishReason = firstCandidate.get("finishReason").getAsString();
            JsonObject content = firstCandidate.getAsJsonObject("content");

            if (content != null) {
                JsonArray parts = content.getAsJsonArray("parts");
                if (parts != null && parts.size() > 0) {
                    String text = parts.get(0).getAsJsonObject().get("text").getAsString().trim();
                    return text.equalsIgnoreCase("TRUE");
                }
            } else {
                if (finishReason.equalsIgnoreCase("SAFETY")) {
                    return false;
                }
            }
        }
        return true;
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
