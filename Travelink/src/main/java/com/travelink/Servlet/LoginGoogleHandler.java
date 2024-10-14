/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.travelink.Servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.travelink.Database.AccountDB;
import com.travelink.Database.ProvinceDB;
import com.travelink.Model.Constants;
import com.travelink.Model.Account;
import com.travelink.Model.Province;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author ASUS
 */
public class LoginGoogleHandler extends HttpServlet {

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
        String code = request.getParameter("code");
        String registerRole = request.getParameter("role");
        String accessToken = getToken(code);

        //Get Account info from google
        Account userGoogle = getAccountInfo(accessToken);
        System.out.println(userGoogle);

        //Search in database 
        Account Account = AccountDB.getAccount(userGoogle.getEmail());

        //If never sign in and use Customer form
        if (Account == null) {
            Account = new Account(userGoogle.getEmail(), userGoogle.getName(), 1, 1);
            Account.setAvatarURL("/Travelink/img_Avatar/avatar_default.jpg");
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder text = new StringBuilder(8);
            Random random = new Random();
            for (int i = 0; i < 8; i++) {
                text.append(characters.charAt(random.nextInt(characters.length())));
            }
            SendEmail mail = new SendEmail();
            Account.setPassword(text.toString());
            mail.sendForgotPassword(Account.getEmail(), text.toString());
            System.out.println(Account);
            AccountDB.insertAccount(Account);
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(60 * 30);
            session.setAttribute("account", Account);
            request.setAttribute("updateMessage", "Your account has been created, your password has been sent to the email you just registered, please change your new password.");
            request.getRequestDispatcher("My_Account_Change.jsp").forward(request, response);

        } //If signed in before and role user
        else if (Account.getRole() == 1) {
            //Account got banned
            if (Account.getStatus() == 2) {
                request.setAttribute("errorLogin", "Your account has been banned!");
                request.getRequestDispatcher("Form_Login.jsp").forward(request, response);
                return;
            }
            HttpSession session = request.getSession();
            session.setAttribute("account", Account);
            session.setMaxInactiveInterval(60 * 30);
            request.setAttribute("succesLogin", "Login successfully.");
            List<Province> locationList = ProvinceDB.getAllProvince();
            request.setAttribute("locationList", locationList);
            request.getRequestDispatcher("Home_Customer.jsp").forward(request, response);
        } //If not the right role
        else if (Account.getRole() != 1) {
            request.setAttribute("errorLogin", "You are logging in with the wrong permission role!");
            request.getRequestDispatcher("Form_Login.jsp").forward(request, response);
        }
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static Account getAccountInfo(String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        Account googlePojo = new Gson().fromJson(response, Account.class);

        return googlePojo;
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
