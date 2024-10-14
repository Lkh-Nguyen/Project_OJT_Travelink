
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.Service;
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
public class ServiceDB implements DatabaseInfo {

    public static Service getServiceByID(int serviceID) {
        Service service = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Service WHERE Service_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, serviceID); // Set the ID parameter
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    service = new Service();
                    service.setServiceID(resultSet.getInt("Service_ID"));
                    service.setName(resultSet.getString("Name"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting service by ID: " + e);
        }
        return service;
    }

    public static List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Service";
                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Service service = new Service();
                    service.setServiceID(resultSet.getInt("Service_ID"));
                    service.setName(resultSet.getString("Name"));
                    services.add(service);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting all services: " + e);
        }
        return services;
    }

    public static List<Service> getServiceByHotelID(int hotelID) {
        List<Service> services = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT s.Service_ID, s.Name "
                        + "FROM Hotel_Service hs "
                        + "JOIN Service s ON hs.Service_ID = s.Service_ID "
                        + "WHERE hs.Hotel_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotelID); // Set the hotel ID parameter
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Service service = new Service();
                    service.setServiceID(resultSet.getInt("Service_ID"));
                    service.setName(resultSet.getString("Name"));
                    services.add(service);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting services by hotel ID: " + e);
        } finally {
            // Close resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
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
        return services;
    }

    public static void main(String[] args) throws SQLException {

        // Test getServiceByID
        System.out.println("\n** Test getServiceByID **");
        int testServiceID = 2; // Replace with an existing service ID
        Service service = ServiceDB.getServiceByID(testServiceID);
        if (service == null) {
            System.out.println("Service with ID " + testServiceID + " not found.");
        } else {
            System.out.println("Details of Service (ID: " + testServiceID + "):");
            System.out.println(service); // Assuming your Service model has a toString() method
        }

        // Test getAllServices
        System.out.println("\n** Test getAllServices **");
        List<Service> services = ServiceDB.getAllServices();
        if (services.isEmpty()) {
            System.out.println("No services found in the database.");
        } else {
            System.out.println("List of all Services:");
            for (Service service1 : services) {
                System.out.println(service1);
            }
        }
    }

}
