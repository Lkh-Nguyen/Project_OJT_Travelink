/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.Facility;
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
public class HotelFacilityDB implements DatabaseInfo {

    public static boolean addHotelFacility(int hotelID, int facilityID) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "INSERT INTO Hotel_Facility (Hotel_ID, Facility_ID) VALUES (?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotelID);
                statement.setInt(2, facilityID);
                int rowsAffected = statement.executeUpdate();

                return rowsAffected > 0; // Check if a row was inserted
            }
        } catch (SQLException e) {
            System.out.println("Error adding hotel-facility relationship: " + e);
        }
        return false;
    }

    public static List<Facility> getFacilitiesByHotelID(int hotelID) {
        List<Facility> facilities = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT f.* FROM Facility f INNER JOIN Hotel_Facility hf ON f.Facility_ID = hf.Facility_ID WHERE hf.Hotel_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotelID);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Facility facility = new Facility();
                    facility.setFacilityID(resultSet.getInt("Facility_ID"));
                    facility.setUrl(resultSet.getString("URL"));
                    facility.setName(resultSet.getString("Name"));
                    facilities.add(facility);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting facilities by hotel ID: " + e);
        }
        return facilities;
    }

    public static List<Integer> getHotelsByFacilityID(int facilityID) {
        List<Integer> hotels = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT hf.Hotel_ID FROM Hotel_Facility hf WHERE hf.Facility_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, facilityID);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    hotels.add(resultSet.getInt("Hotel_ID"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting hotels by facility ID: " + e);
        }
        return hotels;
    }

    public static void main(String[] args) throws SQLException {

        // Test addHotelFacility
        System.out.println("\n** Test addHotelFacility **");
        int testHotelID = 2;
        int testFacilityID = 1;
        if (HotelFacilityDB.addHotelFacility(testHotelID, testFacilityID)) {
            System.out.println("Hotel (ID: " + testHotelID + ") - Facility (ID: " + testFacilityID + ") relationship added successfully.");
        } else {
            System.out.println("Error adding hotel-facility relationship.");
        }

        // Test getFacilitiesByHotelID
        System.out.println("\n** Test getFacilitiesByHotelID **");
        testHotelID = 3; // Replace with an actual hotel ID
        List<Facility> facilities = HotelFacilityDB.getFacilitiesByHotelID(testHotelID);
        if (facilities.isEmpty()) {
            System.out.println("No facilities found for hotel (ID: " + testHotelID + ").");
        } else {
            System.out.println("Facilities offered by Hotel (ID: " + testHotelID + "):");
            for (Facility facility : facilities) {
                System.out.println(facility);
            }
        }

        // Test getHotelsByFacilityID
        System.out.println("\n** Test getHotelsByFacilityID **");
        int testFacilityID1 = 4; // Replace with an actual facility ID
        List<Integer> hotels = HotelFacilityDB.getHotelsByFacilityID(testFacilityID1);
        if (hotels.isEmpty()) {
            System.out.println("No hotels found offering facility (ID: " + testFacilityID1 + ").");
        } else {
            System.out.println("Hotels offering Facility (ID: " + testFacilityID1 + "):");
            for (int hotelID : hotels) {
                System.out.println("Hotel ID: " + hotelID);
            }
        }
    }

}
