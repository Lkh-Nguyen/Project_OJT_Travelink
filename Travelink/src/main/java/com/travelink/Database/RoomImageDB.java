/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.Room;
import com.travelink.Model.RoomImage;
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
public class RoomImageDB implements DatabaseInfo {

    public static List<RoomImage> getAllRoomImages() {
        List<RoomImage> images = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Room_Image";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    RoomImage image = new RoomImage();
                    image.setRoom_Image_ID(resultSet.getInt("Room_Image_ID"));
                    image.setName(resultSet.getString("Name")); // Optional: Name of the image
                    image.setUrl(resultSet.getString("URL"));
                    image.setRoom_ID(resultSet.getInt("Room_ID")); // Foreign key for Room_Type
                    images.add(image);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting all room images: " + e);
        }
        return images;
    }

    public static RoomImage getRoomImageByID(int id) {
        RoomImage image = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Room_Image WHERE Room_Image_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, id); // Set the ID parameter
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    image = new RoomImage();
                    image.setRoom_Image_ID(resultSet.getInt("Room_Image_ID"));
                    image.setName(resultSet.getString("Name")); // Optional: Name of the image
                    image.setUrl(resultSet.getString("URL"));
                    image.setRoom_ID(resultSet.getInt("Room_ID"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting room image by ID: " + e);
        }
        return image;
    }

    public static List<RoomImage> getRoomImagesByRoom_ID(int room_ID) {
        List<RoomImage> images = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Room_Image WHERE Room_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, room_ID); // Set the room ID parameter
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    RoomImage image = new RoomImage();
                    image.setRoom_Image_ID(resultSet.getInt("Room_Image_ID"));
                    image.setName(resultSet.getString("Name")); // Optional: Name of the image
                    image.setUrl(resultSet.getString("URL"));
                    image.setRoom_ID(resultSet.getInt("Room_ID"));
                    images.add(image);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting room images by room ID: " + e);
        }
        return images;
    }

    //Update roomImages by Room_image_id
    public static boolean updateRoomImage(int imageId, String newImagePath) {
        String sql = "UPDATE Room_Image SET URL = ? WHERE Room_Image_ID = ?";
        try (Connection connection = DatabaseInfo.getConnect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newImagePath);
            statement.setInt(2, imageId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was updated
        } catch (SQLException e) {
            System.out.println("Error updating room image: " + e);
            return false;
        }
    }

    //Add Image by Room_ID
    public static boolean insertRoomImage(int roomId, String imagePath) {
        String sql = "INSERT INTO Room_Image (URL, Room_ID) VALUES (?, ?)";
        try (Connection connection = DatabaseInfo.getConnect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, imagePath);
            statement.setInt(2, roomId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was inserted
        } catch (SQLException e) {
            System.out.println("Error inserting room image: " + e);
            return false;
        }
    }

    //Delete image by Room_ID
    //Delete Image by Room_ID
    public static boolean deleteRoomImage(int imageId) {
        String sql = "DELETE FROM Room_Image WHERE Room_Image_ID = ?";
        try (Connection connection = DatabaseInfo.getConnect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, imageId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was deleted
        } catch (SQLException e) {
            System.out.println("Error deleting room image: " + e);
            return false;
        }
    }

    public static void main(String[] args) throws SQLException {

        // Test getAllRoomImages
        System.out.println("** Test getAllRoomImages **");
        List<RoomImage> allImages = RoomImageDB.getAllRoomImages();
        if (allImages.isEmpty()) {
            System.out.println("No room images found in the database.");
        } else {
            System.out.println("List of all room images:");
            for (RoomImage image : allImages) {
                System.out.println(image); // Uses toString() for informative output
            }
        }

        // Test getRoomImageByID
        System.out.println("\n** Test getRoomImageByID **");
        int specificID = 3; // Replace with an existing room image ID
        RoomImage imageByID = RoomImageDB.getRoomImageByID(specificID);
        if (imageByID != null) {
            System.out.println("Room Image Details (ID: " + specificID + "):");
            System.out.println(imageByID);
        } else {
            System.out.println("Room image with ID " + specificID + " not found.");
        }

        // Test getRoomImagesByRoom_ID
        System.out.println("\n** Test getRoomImagesByRoom_ID **");
        int targetRoom_ID = 1; // Replace with an existing room ID
        List<RoomImage> roomTypeImages = RoomImageDB.getRoomImagesByRoom_ID(targetRoom_ID);
        if (roomTypeImages.isEmpty()) {
            System.out.println("No room images found for room with ID " + targetRoom_ID + ".");
        } else {
            System.out.println("Room Images for Room (ID: " + targetRoom_ID + "):");
            for (RoomImage image : roomTypeImages) {
                System.out.println(image);
            }
        }

        //Test insert room image
        System.out.println("\n** Test insertRoomImage **");
        int roomIdToInsert = 4; // Replace with an existing room ID
        String imagePathToInsert = "ck.jpg"; // Replace with a valid image path
        boolean insertResult = RoomImageDB.insertRoomImage(roomIdToInsert, imagePathToInsert);
        if (insertResult) {
            System.out.println("Room image inserted successfully.");
        } else {
            System.out.println("Failed to insert room image.");
        }

    }

}
