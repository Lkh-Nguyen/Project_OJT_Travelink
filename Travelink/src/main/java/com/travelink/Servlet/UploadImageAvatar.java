package com.travelink.Servlet;

import com.travelink.Database.HotelImageDB;
import com.travelink.Database.RoomImageDB;
import com.travelink.Model.Account;
import com.travelink.Model.HotelImage;
import com.travelink.Model.RoomImage;
import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.util.List;

@WebServlet("/UploadFileServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 50)
public class UploadImageAvatar extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uploadHotelHostValue = request.getParameter("uploadhotelhost");
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            fileName = new File(fileName).getName();
            File imagesDir = new File(getServletContext().getRealPath("/img_Avatar"));
            if (!imagesDir.exists()) {
                imagesDir.mkdirs();
            }

            String uploadPath = getServletContext().getRealPath("/img_Avatar") + File.separator + fileName;
            part.write(uploadPath);
            request.setAttribute("uploadedFilePath", fileName);
            break;
        }
        HttpSession session = request.getSession();
        Account sessionAccount = (Account) session.getAttribute("account");
        if (sessionAccount.getRole() == 1) {
            getServletContext().getRequestDispatcher("/View_Avatar.jsp").forward(request, response);
        } else if (sessionAccount.getRole() == 2) {
            if (uploadHotelHostValue.equalsIgnoreCase("2.1")) {

                getServletContext().getRequestDispatcher("/HotelHost_ViewAvatar.jsp").forward(request, response);

            } else if (uploadHotelHostValue.equalsIgnoreCase("2.2")) {

                String room_ID = request.getParameter("roomID");
                int roomid = Integer.parseInt(room_ID);

                List<RoomImage> list_images = RoomImageDB.getRoomImagesByRoom_ID(roomid);

                request.setAttribute("room_ID", roomid);
                request.setAttribute("list_images", list_images);
                request.getRequestDispatcher("HotelHost_RoomImage.jsp").forward(request, response);

            } else if (uploadHotelHostValue.equalsIgnoreCase("2.3")) {

                String room_ID = request.getParameter("roomID");
                int roomid = Integer.parseInt(room_ID);

                List<RoomImage> list_images = RoomImageDB.getRoomImagesByRoom_ID(roomid);

                request.setAttribute("room_ID", roomid);
                request.setAttribute("list_images", list_images);
                request.getRequestDispatcher("HotelHost_RoomImage.jsp").forward(request, response);
                
            } else if (uploadHotelHostValue.equalsIgnoreCase("2.4")) {
                int hotelID = Integer.parseInt(request.getParameter("hotelID"));
                int hotelImgID = Integer.parseInt(request.getParameter("hotelImgID"));
                List<HotelImage> imgList = HotelImageDB.getHotelImagesByHotelID(hotelID);
                request.setAttribute("imageList", imgList);
                request.setAttribute("update", hotelImgID);
                request.setAttribute("hotelID", hotelID);
                request.getRequestDispatcher("HotelHost_Hotel_Image.jsp").forward(request, response);

            } else if (uploadHotelHostValue.equalsIgnoreCase("2.5")) {
                int hotelID = Integer.parseInt(request.getParameter("hotelID"));
                List<HotelImage> imgList = HotelImageDB.getHotelImagesByHotelID(hotelID);
                request.setAttribute("imageList", imgList);
                request.setAttribute("hotelID", hotelID);
                request.getRequestDispatcher("HotelHost_Hotel_Image.jsp").forward(request, response);

            }
        }

    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
