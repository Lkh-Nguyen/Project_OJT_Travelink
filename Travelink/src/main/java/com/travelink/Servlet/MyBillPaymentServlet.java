/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.travelink.Database.BillDB;
import com.travelink.Model.Account;
import com.travelink.Model.Bill;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;


/**
 *
 * @author admin
 */
public class MyBillPaymentServlet extends HttpServlet {

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
            out.println("<title>Servlet MyBillPaymentServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyBillPaymentServlet at " + request.getContextPath() + "</h1>");
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
        String reservationId = request.getParameter("reservation_ID");
        int reservation_ID = Integer.parseInt(reservationId);
        
        
        if (account == null) {
            response.sendRedirect("Form_Login.jsp");
            return;
        }
        
        
        
        //Gán giá trị cứng cho đối số được truyền vào
        List<Bill> list_bill= BillDB.getBillByCustomerIDAndReservationID(account.getAccount_ID(), reservation_ID);
        
        for(Bill bill : list_bill){
            if(reservation_ID != bill.getReservationID()){
                response.sendRedirect("Home_Customer.jsp");
            }
        }
        
        //Needed for Export Bill
        request.setAttribute("reservationID", reservation_ID);
        request.setAttribute("list_bill", list_bill);
        
        // Render JSP to HTML
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ExportBill.jsp");
        dispatcher.include(request, new HttpServletResponseWrapper(response) {
            @Override
            public PrintWriter getWriter() {
                return writer;
            }
        });
        writer.flush();
        String htmlContent = stringWriter.toString();

        // Store the HTML content in session
        session.setAttribute("htmlContent", htmlContent);

        // Forward to the JSP page to show the bill details
        dispatcher = request.getRequestDispatcher("My_Bill_Payment.jsp");
        dispatcher.forward(request, response);        
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
