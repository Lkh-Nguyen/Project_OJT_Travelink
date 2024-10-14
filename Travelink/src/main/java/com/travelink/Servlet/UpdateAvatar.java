package com.travelink.Servlet;

import com.travelink.Database.AccountDB;
import com.travelink.Database.HotelDB;
import com.travelink.Database.HotelImageDB;
import com.travelink.Database.RoomImageDB;
import com.travelink.Model.Account;
import com.travelink.Model.HotelImage;
import com.travelink.Model.RoomImage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author HELLO
 */
public class UpdateAvatar extends HttpServlet {

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
            out.println("<title>Servlet ChangeAvatar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeAvatar at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("View_Avatar.jsp").forward(request, response);
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
        String uploadHotelHostValue = request.getParameter("uploadhotelhost");
        String urlAvatar = request.getParameter("urlAvatar");
        HttpSession session = request.getSession();
        Account sessionAccount = (Account) session.getAttribute("account");

        // Update the user's information
        if (sessionAccount.getRole() == 1) {
            if (AccountDB.updateAvatarAccount(sessionAccount, urlAvatar)) {
                sessionAccount.setAvatarURL(urlAvatar);
                request.setAttribute("updateStatus", "Change avatar successfully.");
                // Update the session with the new user information
                session.setAttribute("account", sessionAccount);
            }
            request.getRequestDispatcher("View_Avatar.jsp").forward(request, response);
        } else if (sessionAccount.getRole() == 2) {
            if (uploadHotelHostValue.equalsIgnoreCase("2.1")) {
                if (AccountDB.updateAvatarAccount(sessionAccount, urlAvatar)) {

                    sessionAccount.setAvatarURL(urlAvatar);

                    request.setAttribute("updateStatus", "Change avatar successfully.");
                    // Update the session with the new user information
                    session.setAttribute("account", sessionAccount);
                }
                request.getRequestDispatcher("HotelHost_ViewAvatar.jsp").forward(request, response);

            //Update Image Room
            } else if (uploadHotelHostValue.equalsIgnoreCase("2.2")) {

                String room_ID = request.getParameter("roomID");
                int roomid = Integer.parseInt(room_ID);

                String room_ImageID = request.getParameter("roomImageID");
                int imageid = Integer.parseInt(room_ImageID);

                if (RoomImageDB.updateRoomImage(imageid, urlAvatar)) {
                    request.setAttribute("updateStatus", "Change Room Image successfully.");
                } else {
                    request.setAttribute("updateStatus", "Change Room Image failed.");
                }

                List<RoomImage> list_images = RoomImageDB.getRoomImagesByRoom_ID(roomid);
                request.setAttribute("list_images", list_images);
                request.setAttribute("room_ID", roomid);
                request.getRequestDispatcher("HotelHost_RoomImage.jsp").forward(request, response);
            
            //Add Image Room    
            } else if (uploadHotelHostValue.equalsIgnoreCase("2.3")) {
                String room_ID = request.getParameter("roomID");
                int roomid = Integer.parseInt(room_ID);

                if (RoomImageDB.insertRoomImage(roomid, urlAvatar)) {
                    request.setAttribute("updateStatus", "Add Room Image successfully.");
                }  else {
                    request.setAttribute("updateStatus", "Add Room Image failed.");
                }

                List<RoomImage> list_images = RoomImageDB.getRoomImagesByRoom_ID(roomid);
                request.setAttribute("list_images", list_images);
                request.setAttribute("room_ID", roomid);
                request.getRequestDispatcher("HotelHost_RoomImage.jsp").forward(request, response);
                
            } else if (uploadHotelHostValue.equalsIgnoreCase("2.4")) {
                int hotelID = Integer.parseInt(request.getParameter("hotelID"));
                int hotelImgID = Integer.parseInt(request.getParameter("hotelImgID"));

                if (HotelImageDB.updateImageUrl(hotelImgID, urlAvatar)) {
                    request.setAttribute("updateStatus", "Change Hotel Image successfully.");
                } else {
                    request.setAttribute("updateStatus", "Change Hotel Image failed.");
                }
                
                
                List<HotelImage> imgList = HotelImageDB.getHotelImagesByHotelID(hotelID);
                request.setAttribute("imageList", imgList);
                request.setAttribute("update", hotelImgID);
                request.getRequestDispatcher("HotelHost_Hotel_Image.jsp").forward(request, response);
            } else if (uploadHotelHostValue.equalsIgnoreCase("2.5")) {
                int hotelID = Integer.parseInt(request.getParameter("hotelID"));

                if (HotelImageDB.addNewHotelImg(hotelID, urlAvatar)) {
                    request.setAttribute("updateStatus", "Add Hotel Image successfully.");
                } else {
                    request.setAttribute("updateStatus", "Add Hotel Image failed.");
                }
                
                List<HotelImage> imgList = HotelImageDB.getHotelImagesByHotelID(hotelID);
                request.setAttribute("imageList", imgList);
                request.setAttribute("hotelID", hotelID);
                request.getRequestDispatcher("HotelHost_Hotel_Image.jsp").forward(request, response);
            }
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
