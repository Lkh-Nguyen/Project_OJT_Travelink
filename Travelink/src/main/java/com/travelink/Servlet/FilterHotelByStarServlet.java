/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@WebServlet(name = "FilterHotelByStarServlet", urlPatterns = {"/filterHotelByStarServlet"})
public class FilterHotelByStarServlet extends HttpServlet {

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
            out.println("<title>Servlet FilterHotelByStarServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterHotelByStarServlet at " + request.getContextPath() + "</h1>");
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
        try {
            // Retrieve parameters from the request
            String star = request.getParameter("star");
            String location = request.getParameter("location");
            String numberOfPeople = request.getParameter("number_of_people");
            String checkInDate = request.getParameter("check_in_date");
            String checkOutDate = request.getParameter("check_out_date");
            String numberOfRooms = request.getParameter("number_of_rooms");

            // Print parameters for debugging
            System.out.println("-----------------------------------------------------");
            System.out.println("Location: " + location);
            System.out.println("Number of people: " + numberOfPeople);
            System.out.println("Check-in date: " + checkInDate);
            System.out.println("Check-out date: " + checkOutDate);
            System.out.println("Number of rooms: " + numberOfRooms);
            System.out.println("Star: " + star);

            // Define the character encoding
            String encoding = "UTF-8";

            // Build the query string with the parameters in the desired sequence
            StringBuilder queryString = new StringBuilder("search?");
            if (location != null && !location.isEmpty()) {
                // Encode location parameter
                queryString.append("location=").append(URLEncoder.encode(location, encoding).replace("%20", "+")).append("&");
            }
            if (numberOfPeople != null && !numberOfPeople.isEmpty()) {
                queryString.append("number_of_people=").append(URLEncoder.encode(numberOfPeople, encoding).replace("%20", "+")).append("&");
            }
            if (checkInDate != null && !checkInDate.isEmpty()) {
                queryString.append("check_in_date=").append(URLEncoder.encode(checkInDate, encoding).replace("%20", "+")).append("&");
            }
            if (checkOutDate != null && !checkOutDate.isEmpty()) {
                queryString.append("check_out_date=").append(URLEncoder.encode(checkOutDate, encoding).replace("%20", "+")).append("&");
            }
            if (numberOfRooms != null && !numberOfRooms.isEmpty()) {
                queryString.append("number_of_rooms=").append(URLEncoder.encode(numberOfRooms, encoding).replace("%20", "+")).append("&");
            }
            if (star != null && !star.isEmpty()) {
                queryString.append("star=").append(URLEncoder.encode(star, encoding).replace("%20", "+")).append("&");
            }

            // Remove the trailing "&" if it exists
            if (queryString.charAt(queryString.length() - 1) == '&') {
                queryString.setLength(queryString.length() - 1);
            }

            // Print the constructed query string for debugging
            System.out.println("Constructed Query String: " + queryString.toString());

            // Redirect to the search page with the built query string
            response.sendRedirect(response.encodeRedirectURL(queryString.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Error.jsp");
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
        processRequest(request, response);
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
