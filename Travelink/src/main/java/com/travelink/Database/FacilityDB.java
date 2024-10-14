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
public class FacilityDB implements DatabaseInfo {

    public static Facility getFacilityByID(int facilityID) throws SQLException {
        Facility facility = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Facility WHERE Facility_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, facilityID); // Set the ID parameter
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    facility = new Facility();
                    facility.setFacilityID(resultSet.getInt("Facility_ID"));
                    facility.setUrl(resultSet.getString("URL"));
                    facility.setName(resultSet.getString("Name"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting facility by ID: " + e);
        }
        return facility;
    }

    public static List<Facility> getAllFacilities() {
        List<Facility> facilities = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Facility";
                statement = connection.prepareStatement(query);
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
            System.out.println("Error getting all facilities: " + e);
        }
        return facilities;
    }

    public static void main(String[] args) throws SQLException {

        // Test getFacilityByID
        System.out.println("\n** Test getFacilityByID **");
        int testFacilityID = 3; // Replace with an existing facility ID
        Facility facility = FacilityDB.getFacilityByID(testFacilityID);
        if (facility == null) {
            System.out.println("Facility with ID " + testFacilityID + " not found.");
        } else {
            System.out.println("Details of Facility (ID: " + testFacilityID + "):");
            System.out.println(facility); // Assuming your Facility model has a toString() method
        }

        // Test getAllFacilities
        System.out.println("\n** Test getAllFacilities **");
        List<Facility> facilities = FacilityDB.getAllFacilities();
        if (facilities.isEmpty()) {
            System.out.println("No facilities found in the database.");
        } else {
            System.out.println("List of all Facilities:");
            for (Facility facility1 : facilities) {
                System.out.println(facility1);
            }
        }
    }
}
