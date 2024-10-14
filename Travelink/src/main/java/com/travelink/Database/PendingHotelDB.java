/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.PendingHotel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class PendingHotelDB implements DatabaseInfo {

    public static PendingHotel getPendingHotelByID(int pendingHotelID) {
        PendingHotel hotel = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Pending_Hotel WHERE Pending_Hotel_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, pendingHotelID); // Set the ID parameter
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    hotel = new PendingHotel();
                    hotel.setPendingHotelID(resultSet.getInt("Pending_Hotel_ID"));
                    hotel.setName(resultSet.getString("Name"));
                    hotel.setEmail(resultSet.getString("Email"));
                    hotel.setPassword(resultSet.getString("Password")); // Consider security implications of storing plain text passwords
                    hotel.setPhoneNumber(resultSet.getString("PhoneNumber"));
                    hotel.setAddress(resultSet.getString("Address"));
                    hotel.setWardID(resultSet.getInt("Ward_ID"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting pending hotel by ID: " + e);
        }
        return hotel;
    }

    public static List<PendingHotel> getHotelsByWardID(int wardID) {
        List<PendingHotel> hotels = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Pending_Hotel WHERE Ward_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, wardID); // Set the ward ID parameter
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    PendingHotel hotel = new PendingHotel();
                    hotel.setPendingHotelID(resultSet.getInt("Pending_Hotel_ID"));
                    hotel.setName(resultSet.getString("Name"));
                    hotel.setEmail(resultSet.getString("Email"));
                    hotel.setPassword(resultSet.getString("Password")); // Consider security implications of storing plain text passwords
                    hotel.setPhoneNumber(resultSet.getString("PhoneNumber"));
                    hotel.setAddress(resultSet.getString("Address"));
                    hotel.setWardID(resultSet.getInt("Ward_ID"));
                    hotels.add(hotel);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting pending hotels by ward ID: " + e);
        }
        return hotels;
    }

    public static void main(String[] args){

        // Test getPendingHotelByID
        System.out.println("\n** Test getPendingHotelByID **");
        int testHotelID = 3; // Replace with an existing pending hotel ID
        PendingHotel hotel = PendingHotelDB.getPendingHotelByID(testHotelID);
        if (hotel == null) {
            System.out.println("Pending Hotel with ID " + testHotelID + " not found.");
        } else {
            System.out.println("Details of Pending Hotel (ID: " + testHotelID + "):");
            System.out.println(hotel); // Assuming your PendingHotel model has a toString() method
        }

        // Test getHotelsByWardID
        System.out.println("\n** Test getHotelsByWardID **");
        int testWardID = 1; // Replace with an existing ward ID
        List<PendingHotel> hotels = PendingHotelDB.getHotelsByWardID(testWardID);
        if (hotels.isEmpty()) {
            System.out.println("No pending hotels found in Ward with ID " + testWardID + ".");
        } else {
            System.out.println("Pending Hotels in Ward (ID: " + testWardID + "):");
            for (PendingHotel pendingHotel : hotels) {
                System.out.println(pendingHotel);
            }
        }
    }

}
