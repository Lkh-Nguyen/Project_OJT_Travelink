/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.HotelImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class HotelImageDB implements DatabaseInfo {

    public static List<HotelImage> getAllHotelImages() {
        List<HotelImage> images = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Hotel_Image";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    HotelImage image = new HotelImage();
                    image.setHotelImageID(resultSet.getInt("Hotel_Image_ID"));
                    image.setName(resultSet.getString("Name"));
                    image.setUrl(resultSet.getString("URL"));
                    image.setHotelID(resultSet.getInt("Hotel_ID"));
                    images.add(image);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting all hotel images: " + e);
        }
        return images;
    }

    public static HotelImage getHotelImageByID(int id) {
        HotelImage image = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Hotel_Image WHERE Hotel_Image_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, id); // Set the ID parameter
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    image = new HotelImage();
                    image.setHotelImageID(resultSet.getInt("Hotel_Image_ID"));
                    image.setName(resultSet.getString("Name"));
                    image.setUrl(resultSet.getString("URL"));
                    image.setHotelID(resultSet.getInt("Hotel_ID"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting hotel image by ID: " + e);
        }
        return image;
    }

    public static boolean deleteHotelImgByID(int ID) {
        boolean deleted = false;

        String query = "DELETE FROM Hotel_Image WHERE Hotel_Image_ID=?";
        try (Connection connection = DatabaseInfo.getConnect(); PreparedStatement statement = connection.prepareStatement(query)) {

            if (connection != null) {
                statement.setInt(1, ID);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    deleted = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error deleting hotel image: " + e);
        }
        return deleted;
    }

    public static boolean updateImageUrl(int imageID, String newUrl) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean updated = false;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "UPDATE Hotel_Image SET URL=? WHERE Hotel_Image_ID=?";
                statement = connection.prepareStatement(query);

                statement.setString(1, newUrl);
                statement.setInt(2, imageID);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    updated = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error updating hotel image URL: " + e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
        return updated;
    }

    public static boolean addNewHotelImg(int hotelID, String imageUrl) {
        boolean added = false;
        String query = "INSERT INTO Hotel_Image (Hotel_ID, URL) VALUES (?, ?)";

        try (Connection connection = DatabaseInfo.getConnect(); PreparedStatement statement = connection.prepareStatement(query)) {

            if (connection != null) {
                statement.setInt(1, hotelID);
                statement.setString(2, imageUrl);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    added = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding new hotel image: " + e);
        }

        return added;
    }

    public static List<HotelImage> getHotelImagesByHotelID(int hotelID) {
        List<HotelImage> images = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Hotel_Image WHERE Hotel_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotelID); // Set the hotel ID parameter
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    HotelImage image = new HotelImage();
                    image.setHotelImageID(resultSet.getInt("Hotel_Image_ID"));
                    image.setName(resultSet.getString("Name"));
                    image.setUrl(resultSet.getString("URL"));
                    image.setHotelID(resultSet.getInt("Hotel_ID"));
                    images.add(image);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting hotel images by hotel ID: " + e);
        }
        return images;
    }

    public static void main(String[] args) throws SQLException {

        // Test getAllHotelImages
        System.out.println("** Test getAllHotelImages **");
        List<HotelImage> allImages = HotelImageDB.getAllHotelImages();
        if (allImages.isEmpty()) {
            System.out.println("No hotel images found in the database.");
        } else {
            System.out.println("List of all hotel images:");
            for (HotelImage image : allImages) {
                System.out.println(image); // Uses toString() for informative output
            }
        }

        // Test getHotelImageByID
        System.out.println("\n** Test getHotelImageByID **");
        int specificID = 2; // Replace with an existing hotel image ID
        HotelImage imageByID = HotelImageDB.getHotelImageByID(specificID);
        if (imageByID != null) {
            System.out.println("Hotel Image Details (ID: " + specificID + "):");
            System.out.println(imageByID);
        } else {
            System.out.println("Hotel image with ID " + specificID + " not found.");
        }

        // Test getHotelImagesByHotelID
        System.out.println("\n** Test getHotelImagesByHotelID **");
        int targetHotelID = 1; // Replace with an existing hotel ID
        List<HotelImage> hotelImages = HotelImageDB.getHotelImagesByHotelID(targetHotelID);
        if (hotelImages.isEmpty()) {
            System.out.println("No images found for hotel with ID " + targetHotelID + ".");
        } else {
            System.out.println("Images for Hotel (ID: " + targetHotelID + "):");
            for (HotelImage image : hotelImages) {
                System.out.println(image);
            }
        }
    }

}
