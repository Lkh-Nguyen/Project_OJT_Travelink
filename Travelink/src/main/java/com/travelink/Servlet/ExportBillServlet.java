/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class ExportBillServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the HTML content from session
        HttpSession session = request.getSession();
        String htmlContent = (String) session.getAttribute("htmlContent");
        System.out.println(htmlContent);
        if (htmlContent == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No bill information available");
            return;
        }

        // Set response headers
        response.setContentType("application/pdf; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"output.pdf\"");

        // Generate PDF from HTML
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(htmlContent, null); // Ensure htmlContent is UTF-8 encoded
            builder.toStream(os);
            builder.run();

            byte[] pdfBytes = os.toByteArray();
            response.getOutputStream().write(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to generate PDF");
        }
    }

    @Override
    public String getServletInfo() {
        return "ExportBillServlet generates a PDF file from HTML content stored in session.";
    }
}
