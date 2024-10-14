/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.Reservation;
import com.travelink.Model.Ward;
import com.travelink.Servlet.SendEmail;
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
public class WardDB implements DatabaseInfo {

    public static List<Ward> getAllWards() {
        List<Ward> wards = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Ward";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Ward ward = new Ward();
                    ward.setWard_ID(resultSet.getInt("Ward_ID"));
                    ward.setName(resultSet.getString("Name"));
                    ward.setDistrict_ID(resultSet.getInt("District_ID"));
                    wards.add(ward);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting all wards: " + e);
        }

        return wards;
    }

    public static Ward getWardByID(int id) {
        Ward ward = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Ward WHERE Ward_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, id); // Set the ID parameter
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    ward = new Ward();
                    ward.setWard_ID(resultSet.getInt("Ward_ID"));
                    ward.setName(resultSet.getString("Name"));
                    ward.setDistrict_ID(resultSet.getInt("District_ID"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting ward by ID: " + e);
        }
        return ward;
    }

    public static List<Ward> getWardsByDistrictID(int districtID) {
        List<Ward> wards = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Ward WHERE District_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, districtID); // Set the district ID parameter
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Ward ward = new Ward();
                    ward.setWard_ID(resultSet.getInt("Ward_ID"));
                    ward.setName(resultSet.getString("Name"));
                    ward.setDistrict_ID(resultSet.getInt("District_ID"));
                    wards.add(ward);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting wards by district ID: " + e);
        }

        return wards;
    }

//    public static void main(String[] args) throws SQLException {
//
//        // Test getAllWards
//        System.out.println("** Test getAllWards **");
//        List<Ward> allWards = WardDB.getAllWards();
//        if (allWards.isEmpty()) {
//            System.out.println("No wards found in the database.");
//        } else {
//            System.out.println("List of all wards:");
//            for (Ward ward : allWards) {
//                System.out.println(ward); // Uses toString() for informative output
//            }
//        }
//
//        // Test getWardByID
//        System.out.println("\n** Test getWardByID **");
//        int specificID = 5; // Replace with an existing ward ID
//        Ward wardByID = WardDB.getWardByID(specificID);
//        if (wardByID != null) {
//            System.out.println("Ward Details (ID: " + specificID + "):");
//            System.out.println(wardByID);
//        } else {
//            System.out.println("Ward with ID " + specificID + " not found.");
//        }
//
//        // Test getWardsByDistrictID
//        System.out.println("\n** Test getWardsByDistrictID **");
//        int targetDistrictID = 2; // Replace with an existing district ID
//        List<Ward> wardsInDistrict = WardDB.getWardsByDistrictID(targetDistrictID);
//        if (wardsInDistrict.isEmpty()) {
//            System.out.println("No wards found in district " + targetDistrictID + ".");
//        } else {
//            System.out.println("Wards in District " + targetDistrictID + ":");
//            for (Ward ward : wardsInDistrict) {
//                System.out.println(ward);
//            }
//        }
//    }
    
    public static void main(String[] args) {
        SendEmail sendEmail = new SendEmail();
        Reservation reservation = ReservationDB.getReservationByReservationID(81);
        sendEmail.sendSuccessReservation("woolythesheepnew@gmail.com", reservation);
        System.out.println("Send success!");
    }
    
}
