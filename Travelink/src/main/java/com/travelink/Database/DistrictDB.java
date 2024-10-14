/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.District;
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
public class DistrictDB implements DatabaseInfo {

    public static List<District> getAllDistricts() {
        List<District> districts = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM District";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    District district = new District();
                    district.setDistrict_ID(resultSet.getInt("District_ID"));
                    district.setName(resultSet.getString("Name"));
                    district.setProvince_ID(resultSet.getInt("Province_ID"));
                    districts.add(district);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting all districts: " + e);
        }
        return districts;
    }

    public static District getDistrictByID(int id) {
        District district = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM District WHERE District_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, id); // Set the ID parameter
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    district = new District();
                    district.setDistrict_ID(resultSet.getInt("District_ID"));
                    district.setName(resultSet.getString("Name"));
                    district.setProvince_ID(resultSet.getInt("Province_ID"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting district by ID: " + e);
        }
        return district;
    }

    public static List<District> getDistrictsByProvinceID(int provinceID) {
        List<District> districts = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM District WHERE Province_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, provinceID); // Set the province ID parameter
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    District district = new District();
                    district.setDistrict_ID(resultSet.getInt("District_ID"));
                    district.setName(resultSet.getString("Name"));
                    district.setProvince_ID(resultSet.getInt("Province_ID"));
                    districts.add(district);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting districts by province ID: " + e);
        }
        return districts;
    }

    public static void main(String[] args) throws SQLException {

        // Test getAllDistricts
        System.out.println("** Test getAllDistricts **");
        List<District> allDistricts = DistrictDB.getAllDistricts();
        if (allDistricts.isEmpty()) {
            System.out.println("No districts found in the database.");
        } else {
            System.out.println("List of all districts:");
            for (District district : allDistricts) {
                System.out.println(district); // Uses toString() for informative output
            }
        }

        // Test getDistrictByID
        System.out.println("\n** Test getDistrictByID **");
        int specificID = 2; // Replace with an existing district ID
        District districtByID = DistrictDB.getDistrictByID(specificID);
        if (districtByID != null) {
            System.out.println("District Details (ID: " + specificID + "):");
            System.out.println(districtByID);
        } else {
            System.out.println("District with ID " + specificID + " not found.");
        }

        // Test getDistrictsByProvinceID
        System.out.println("\n** Test getDistrictsByProvinceID **");
        int targetProvinceID = 3; // Replace with an existing province ID
        List<District> districtsInProvince = DistrictDB.getDistrictsByProvinceID(targetProvinceID);
        if (districtsInProvince.isEmpty()) {
            System.out.println("No districts found in province " + targetProvinceID + ".");
        } else {
            System.out.println("Districts in Province " + targetProvinceID + ":");
            for (District district : districtsInProvince) {
                System.out.println(district);
            }
        }
    }

}
